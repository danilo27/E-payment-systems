package pc.payments.impl;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.paypal.api.payments.Agreement;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.ChargeModels;
import com.paypal.api.payments.Currency;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.MerchantPreferences;
import com.paypal.api.payments.Patch;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentDefinition;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.Plan;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.ShippingAddress;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import pc.dto.PaymentConfirmationDto;
import pc.dto.StringDto;
import pc.dto.SubscriptionConfirmation;
import pc.dto.SubscriptionRequest;
import pc.model.Cart;
import pc.model.TransactionResult;
import pc.payments.IPaymentExtensionPoint;
import pc.repositories.MerchantInfoRepository;

@Service
public class PaypalService implements IPaymentExtensionPoint {
	
	@Autowired
	private MerchantInfoRepository merchantInfoRepository;
	
	private String frontendPort = "http://localhost:4200";
	private String production = "sandbox";
	
	private static String payPalApiKey;
	private static String payPalApiPass;

	@Override
	public ResponseEntity<StringDto> prepareTransaction(Cart cart) {
		
		payPalApiKey = merchantInfoRepository.findMerchantData("Paypal", cart.getMerchantId(), "paypalApiKey").getValue();
		payPalApiPass = merchantInfoRepository.findMerchantData("Paypal", cart.getMerchantId(), "paypalApiPassword").getValue();
				
		APIContext context = new APIContext(payPalApiKey, payPalApiPass, production);
		
		StringDto result = new StringDto("");
		Amount amount = new Amount();
		amount.setCurrency("USD");
		amount.setTotal(cart.getTotalPrice().toString());
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);

		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");

		Payment payment = new Payment();
		payment.setIntent("sale");
		payment.setPayer(payer);
		payment.setTransactions(transactions);

		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl(frontendPort + "/paypal-cancel");
		redirectUrls.setReturnUrl(frontendPort + "/paypal-success");
		payment.setRedirectUrls(redirectUrls);

		Payment createdPayment;
		try {
			String redirectUrl = "";
			createdPayment = payment.create(context);
			if (createdPayment != null) {
				if(createdPayment.getState().equals("failed"))
					redirectUrl = frontendPort + "/paypal-error";
				
				List<Links> links = createdPayment.getLinks();
				for (Links link : links) {
					if (link.getRel().equals("approval_url")) {
						redirectUrl = link.getHref();
						break;
					}
				}
				result.setValue(redirectUrl);
				return new ResponseEntity<> (result, HttpStatus.OK);
			}
		} catch (PayPalRESTException e) {
			result.setValue(frontendPort + "/paypal-error");
			System.out.println("Error happened during payment creation!");
		}
		return null;
	}

	@Override
	public TransactionResult proceedTransaction(PaymentConfirmationDto req) {
		TransactionResult result = new TransactionResult();
		Payment payment = new Payment();
		payment.setId(req.getPaymentId());
		
		PaymentExecution paymentExecution = new PaymentExecution();
		paymentExecution.setPayerId(req.getPayerId());
		try {
			APIContext context = new APIContext(payPalApiKey, payPalApiPass, production);
			Payment createdPayment = payment.execute(context, paymentExecution);
			if (createdPayment != null) {
				result.setSuccessMessage("Success");
				System.out.println("SUCESS");
				return result;
			}
		} catch (PayPalRESTException e) {
			System.err.println(e.getDetails());
			result.setSuccessMessage("Failed");
			return result;
		}
		return null;
	}

	@Override
	public TransactionResult prepareSubscription(SubscriptionRequest req) {
		APIContext context = new APIContext(req.getPaypalApiKey(), req.getPaypalApiPassword(), production);
		payPalApiKey = req.getPaypalApiKey();
		payPalApiPass = req.getPaypalApiPassword();
				
		TransactionResult transactionResult = new TransactionResult();
		transactionResult.setRedirectUrl("");

		Plan plan = createPlan(req.getPlanName(), req.getPlanDescription());
		PaymentDefinition paymentDefinition = createPaymentDefinition(req.getFrequency(), req.getFrequencyInterval(), req.getCycles());
		Currency currency = createCurrency(req.getCurrency(), req.getAmount());
		paymentDefinition.setAmount(currency);

		// charge_models
		ChargeModels chargeModels = new com.paypal.api.payments.ChargeModels();
		chargeModels.setType("SHIPPING");
		chargeModels.setAmount(currency);
		List<ChargeModels> chargeModelsList = new ArrayList<ChargeModels>();
		chargeModelsList.add(chargeModels);
		paymentDefinition.setChargeModels(chargeModelsList);

		// payment_definition
		List<PaymentDefinition> paymentDefinitionList = new ArrayList<PaymentDefinition>();
		paymentDefinitionList.add(paymentDefinition);
		plan.setPaymentDefinitions(paymentDefinitionList);

		// merchant_preferences
		MerchantPreferences merchantPreferences = new MerchantPreferences();
		merchantPreferences.setSetupFee(currency);
		merchantPreferences.setCancelUrl(frontendPort + "/paypal-cancel");
		merchantPreferences.setReturnUrl(frontendPort + "/paypal-success-sub");
		merchantPreferences.setMaxFailAttempts("0");
		merchantPreferences.setAutoBillAmount("YES");
		merchantPreferences.setInitialFailAmountAction("CONTINUE");
		plan.setMerchantPreferences(merchantPreferences);

		Plan p;
		String activatedPlanId = "";
		try {
			p = plan.create(context);
			// AKTIVIRANJE PLANA

			List<Patch> patchRequestList = new ArrayList<Patch>();
			Map<String, String> value = new HashMap<String, String>();
			value.put("state", "ACTIVE");

			Patch patch = new Patch();
			patch.setPath("/");
			patch.setValue(value);
			patch.setOp("replace");
			patchRequestList.add(patch);

			p.update(context, patchRequestList);
			activatedPlanId = p.getId();
			// System.out.println(Plan.get(context, p.getId()));

		} catch (PayPalRESTException e1) {
			// TODO Auto-generated catch block
			transactionResult.setRedirectUrl(frontendPort + "/paypal-error");
			e1.printStackTrace();
		}
		// Kreiranje agreement-a

		Agreement agreement = createAgreement();

		Plan pom = new Plan();
		pom.setId(activatedPlanId);
		agreement.setPlan(pom);

		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");
		agreement.setPayer(payer);

		// Set shipping address information
		ShippingAddress shipping = createShipping(req.getShippingAddress(),req.getCity(), req.getStateCode(), req.getPostalCode(), req.getCountryCode());
		agreement.setShippingAddress(shipping);

		try {
			agreement = agreement.create(context);
			// System.out.println("***********");
			// System.out.println(agreement);

			for (Links links : agreement.getLinks()) {
				if ("approval_url".equals(links.getRel())) {
					transactionResult.setRedirectUrl(links.getHref());
					break;
				}
			}
		} catch (PayPalRESTException e) {
			transactionResult.setRedirectUrl(frontendPort + "/paypal-error");
			System.err.println(e.getDetails());
		} catch (MalformedURLException e) {
			transactionResult.setRedirectUrl(frontendPort + "/paypal-error");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			transactionResult.setRedirectUrl(frontendPort + "/paypal-error");
			e.printStackTrace();
		}

		return transactionResult;
	}

	@Override
	public TransactionResult proceedSubscription(SubscriptionConfirmation req) {
		// TODO Auto-generated method stub
		APIContext context = new APIContext(payPalApiKey, payPalApiPass, production);
		TransactionResult transactionResult = new TransactionResult();

		Agreement agreement = new Agreement();
		agreement.setToken(req.getToken());

		try {
			Agreement activeAgreement = Agreement.execute(context, agreement.getToken());
			System.out.println("Agreement created with ID " + activeAgreement.getId());
			transactionResult.setSuccessMessage("Success");
		} catch (PayPalRESTException e) {
			System.err.println(e.getDetails());
			transactionResult.setSuccessMessage("Failed");
		}
		return transactionResult;

	}
	
	private ShippingAddress createShipping(String shippingAddress, String city, String stateCode, String postalCode, String countryCode) {
		ShippingAddress shipping = new ShippingAddress();
		shipping.setLine1(shippingAddress);
		shipping.setCity(city);
		shipping.setState(stateCode);
		shipping.setPostalCode(postalCode);
		shipping.setCountryCode(countryCode);
		return shipping;
	}

	
	private Plan createPlan(String planName, String planDescription) {
		// Build Plan object
		Plan plan = new Plan();
		plan.setName(planName);
		plan.setDescription(planDescription);
		plan.setType("fixed");
		return plan;
	}
	

	private PaymentDefinition createPaymentDefinition(String frequency, String frequencyInterval, String cycles) {
		PaymentDefinition paymentDefinition = new PaymentDefinition();
		paymentDefinition.setName("Regular Payments");
		paymentDefinition.setType("REGULAR");
		paymentDefinition.setFrequency(frequency);
		paymentDefinition.setFrequencyInterval(frequencyInterval);
		paymentDefinition.setCycles(cycles);
		return paymentDefinition;
	}
	

	private Agreement createAgreement() {
		Agreement agreement = new Agreement();
		agreement.setName("Subscription Agreement");
		agreement.setDescription("Basic Agreement");
		Date date = new Date();
		SimpleDateFormat sdf;
		sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");//2018-12-07T13:00:04Z
		sdf.setTimeZone(TimeZone.getTimeZone("CET"));
		String currentDateISO8601 = sdf.format(date) + "Z";
		agreement.setStartDate(currentDateISO8601);
		return agreement;
	}

	private Currency createCurrency(String currency, String amount) {
		Currency c = new Currency();
		c.setCurrency(currency);
		c.setValue(amount);
		return c;
	}

}
