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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

import javassist.NotFoundException;
import pc.dto.PaymentConfirmationDto;
import pc.dto.StringDto;
import pc.dto.SubscriptionConfirmation;
import pc.dto.SubscriptionRequest;
import pc.model.Cart;
import pc.model.Merchant;
import pc.model.TransactionResult;
import pc.payments.IPaymentExtensionPoint;
import pc.repositories.CartRepository;
import pc.repositories.MerchantInfoRepository;
import pc.repositories.MerchantRepository;

@Service
public class PaypalService implements IPaymentExtensionPoint {

	@Autowired
	private MerchantInfoRepository merchantInfoRepository;
	
	@Autowired
	private MerchantRepository merchantRepository;

	@Autowired
	private CartRepository cartRepository;
	
	@Value("${nc.frontend}")
	private String ncFrontend;

	private String production = "sandbox";
	private String proceedEndpoint = "http://localhost:8080/api/payment/confirm/Paypal";
	private String proceedSubscriptionEndpoint = "http://localhost:8080/api/payment/confirm/subscription/Paypal";
	
	private String cancelEndpoint = "http://localhost:8080/api/payment/cancel/Paypal";
	private String errorEndpoint = "http://localhost:8080/api/payment/error/Paypal";

	private static String payPalApiKey;
	private static String payPalApiPass;


	@Override
	public ResponseEntity<StringDto> prepareTransaction(Cart cart) {

		payPalApiKey = merchantInfoRepository.findMerchantData("Paypal", cart.getMerchantId(), "paypalApiKey")
				.getValue();
		payPalApiPass = merchantInfoRepository.findMerchantData("Paypal", cart.getMerchantId(), "paypalApiPassword")
				.getValue();
		/*
		Merchant merchant = merchantRepository.findByMerchantId(cart.getMerchantId());
		
		String errorUrl = merchant.getErrorUrl();
		String cancelUrl = merchant.getFailedUrl();
		*/
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
		redirectUrls.setCancelUrl(cancelEndpoint + "/" + cart.getId());			//MERCHANTOV URL
		redirectUrls.setReturnUrl(proceedEndpoint);
		payment.setRedirectUrls(redirectUrls);

		Payment createdPayment;
		try {
			String redirectUrl = "";
			APIContext context = new APIContext(payPalApiKey, payPalApiPass, production);
			createdPayment = payment.create(context);
			if (createdPayment != null) {
				cart.setPaymentId(createdPayment.getId());
				cartRepository.save(cart);
				
				if (createdPayment.getState().equals("failed"))
					result = saveError(result, cart, errorEndpoint);

				List<Links> links = createdPayment.getLinks();
				for (Links link : links) {
					if (link.getRel().equals("approval_url")) {
						redirectUrl = link.getHref();
						result = saveSuccess(result, redirectUrl);
						break;
					}
				}
			}
		} catch (PayPalRESTException e) {
			result = saveError(result, cart, errorEndpoint);
			System.out.println("Error happened during payment creation!");
		} catch (NullPointerException e) {
			result = saveError(result, cart, errorEndpoint);
			System.out.println("paypal exception durning context creation " + e);
		} finally {
			sendToNc(cart);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	private StringDto saveSuccess(StringDto result, String redirectUrl) {
		result.setValue(redirectUrl);
		return result;
	}

	private StringDto saveError(StringDto result, Cart cart, String errorUrl) {
		result.setValue(errorUrl + "/" + cart.getId());		//MERCHANTOV URL
		return result;
	}

	@Override
	public TransactionResult proceedTransaction(PaymentConfirmationDto req) {
		TransactionResult result = new TransactionResult();
		Payment payment = new Payment();
		payment.setId(req.getPaymentId());
		
		Cart cart = cartRepository.findByPaymentId(req.getPaymentId());
		Merchant m = merchantRepository.findByMerchantId(cart.getMerchantId());
		
		String successUrl = m.getSuccessUrl();
		String errorUrl = m.getErrorUrl();
		
		PaymentExecution paymentExecution = new PaymentExecution();
		paymentExecution.setPayerId(req.getPayerId());
		try {
			APIContext context = new APIContext(payPalApiKey, payPalApiPass, production);
			Payment createdPayment = payment.execute(context, paymentExecution);
			if (createdPayment != null) {
				cart.setStatus("success");
				cartRepository.save(cart);
				result.setSuccessMessage("Success");
				result.setRedirectUrl(successUrl);
				System.out.println("PAYMENT EXECUTED SUCCESFULLY");
			}
		} catch (PayPalRESTException e) {
			result = saveErrorProceed(result, cart, errorUrl);
			System.err.println(e.getDetails());
		}  catch (NullPointerException e) {
			result = saveErrorProceed(result, cart, errorUrl);
			System.out.println("paypal exception durning context creation " + e);
		} finally {
			sendToNc(cart);
		}
		return result;
	}

	private void sendToNc(Cart cart) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Boolean> res = restTemplate.postForEntity("http://localhost:8080/api/pc/returnToPc", cart, Boolean.class);
	}

	private TransactionResult saveErrorProceed(TransactionResult result, Cart cart, String errorUrl) {
		// TODO Auto-generated method stub
		cart.setStatus("error");
		cartRepository.save(cart);
		result.setRedirectUrl(errorUrl);
		result.setSuccessMessage("Failed");
		return result;
	}

	@Override
	public TransactionResult prepareSubscription(SubscriptionRequest req) {
		APIContext context = new APIContext(req.getPaypalApiKey(), req.getPaypalApiPassword(), production);
		payPalApiKey = req.getPaypalApiKey();
		payPalApiPass = req.getPaypalApiPassword();

		TransactionResult transactionResult = new TransactionResult();
		transactionResult.setRedirectUrl("");

		Plan plan = createPlan(req.getPlanName(), req.getPlanDescription());
		PaymentDefinition paymentDefinition = createPaymentDefinition(req.getFrequency(), req.getFrequencyInterval(),
				req.getCycles());
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
		merchantPreferences.setCancelUrl(ncFrontend + "/#/failed");
		merchantPreferences.setReturnUrl(proceedSubscriptionEndpoint);
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
			transactionResult.setRedirectUrl(ncFrontend + "/#/error");
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
		ShippingAddress shipping = createShipping(req.getShippingAddress(), req.getCity(), req.getStateCode(),
				req.getPostalCode(), req.getCountryCode());
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
			transactionResult.setRedirectUrl(ncFrontend + "/#/error");
			System.err.println(e.getDetails());
		} catch (MalformedURLException e) {
			transactionResult.setRedirectUrl(ncFrontend + "/#/error");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			transactionResult.setRedirectUrl(ncFrontend + "/#/error");
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
			transactionResult.setRedirectUrl(ncFrontend + "/#/success");
		} catch (PayPalRESTException e) {
			System.err.println(e.getDetails());
			transactionResult.setSuccessMessage("Failed");
			transactionResult.setRedirectUrl(ncFrontend + "/#/error");
		}
		return transactionResult;

	}

	private ShippingAddress createShipping(String shippingAddress, String city, String stateCode, String postalCode,
			String countryCode) {
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
		sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");// 2018-12-07T13:00:04Z
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

	@Override
	public StringDto cancelTransaction(Long cartId){
		try {
			Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new NotFoundException("cart with id " + cartId + " not found"));
			cart.setStatus("cancelled");
			cartRepository.save(cart);
			sendToNc(cart);
			Merchant merchant = merchantRepository.findByMerchantId(cart.getMerchantId());
			return new StringDto(merchant.getFailedUrl());
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public StringDto errorTransaction(Long cartId) {
		try {
			Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new NotFoundException("cart with id " + cartId + " not found"));
			cart.setStatus("error");
			cartRepository.save(cart);
			sendToNc(cart);
			Merchant merchant = merchantRepository.findByMerchantId(cart.getMerchantId());
			return new StringDto(merchant.getErrorUrl());
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
