package pc.payments.impl;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.paypal.api.payments.Agreement;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.ChargeModels;
import com.paypal.api.payments.Currency;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.MerchantPreferences;
import com.paypal.api.payments.Patch;
import com.paypal.api.payments.Payee;
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
import pc.dto.SubscriptionConfirmation;
import pc.dto.SubscriptionRequest;
import pc.model.PaymentRequest;
import pc.model.TransactionResult;
import pc.payments.IPaymentExtensionPoint;

@Service
public class PayPalService implements IPaymentExtensionPoint {

	private String clientId = "AWSFgD4EBA8g6SrzszTOTrtw5PfBEalEMszEWja7eo9eZNJHt9QgxRdglWGRrqNL1sICvMKhWKolE71o";
	private String clientSecret = "EAbj-IqR0uJb2-mNM8pX1e-3e_ZoYJ4hkiU11xct6T_TMM4uH1P9nrnNi4_hBDWqJGbhEuiL9uTejSbr";
	private String frontendPort = "http://localhost:4200";
	private String production = "sandbox";

	@Override
	public TransactionResult prepareTransaction(PaymentRequest req) {
		TransactionResult result = new TransactionResult();
		Amount amount = new Amount();
		amount.setCurrency(req.getCurrency());
		amount.setTotal(req.getAmount().toString());
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		Payee payee = new Payee();
		payee.setEmail(req.getPayee());
		transaction.setPayee(payee);
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);

		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");

		Payment payment = new Payment();
		payment.setIntent("sale");
		payment.setPayer(payer);
		payment.setTransactions(transactions);

		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl(frontendPort + "/paypal-error");
		redirectUrls.setReturnUrl(frontendPort + "/paypal-success");
		payment.setRedirectUrls(redirectUrls);

		Payment createdPayment;
		try {
			String redirectUrl = "";
			APIContext context = new APIContext(clientId, clientSecret, production);
			createdPayment = payment.create(context);
			if (createdPayment != null) {
				List<Links> links = createdPayment.getLinks();
				for (Links link : links) {
					if (link.getRel().equals("approval_url")) {
						redirectUrl = link.getHref();
						break;
					}
				}
				result.setRedirectUrl(redirectUrl);
				return result;
			}
		} catch (PayPalRESTException e) {
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
			APIContext context = new APIContext(clientId, clientSecret, production);
			Payment createdPayment = payment.execute(context, paymentExecution);
			if (createdPayment != null) {
				result.setSuccessMessage("Success");
				return result;
			}
		} catch (PayPalRESTException e) {
			System.err.println(e.getDetails());
		}
		return null;
	}

	@Override
	public TransactionResult prepareSubscription(SubscriptionRequest req) {
		APIContext context = new APIContext(clientId, clientSecret, "sandbox");
		TransactionResult transactionResult = new TransactionResult();
		transactionResult.setRedirectUrl("");

		// Build Plan object
		Plan plan = new Plan();
		plan.setName("T-Shirt of the Month Club Plan");
		plan.setDescription("Template creation.");
		plan.setType("fixed");

		// payment_definitions
		PaymentDefinition paymentDefinition = new PaymentDefinition();
		paymentDefinition.setName("Regular Payments");
		paymentDefinition.setType("REGULAR");
		paymentDefinition.setFrequency("MONTH");
		paymentDefinition.setFrequencyInterval("1");
		paymentDefinition.setCycles("12");

		// currency
		Currency currency = new Currency();
		currency.setCurrency("USD");
		currency.setValue("20");
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
			e1.printStackTrace();
		}

		// Kreiranje agreement-a

		Agreement agreement = new Agreement();
		agreement.setName("Base Agreement");
		agreement.setDescription("Basic Agreement");
		agreement.setStartDate("2018-12-07T13:00:04Z");

		Plan pom = new Plan();
		pom.setId(activatedPlanId);
		agreement.setPlan(pom);

		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");
		agreement.setPayer(payer);

		// Set shipping address information
		ShippingAddress shipping = new ShippingAddress();
		shipping.setLine1("111 First Street");
		shipping.setCity("Saratoga");
		shipping.setState("CA");
		shipping.setPostalCode("95070");
		shipping.setCountryCode("US");
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
			System.err.println(e.getDetails());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return transactionResult;
	}

	@Override
	public TransactionResult proceedSubscription(SubscriptionConfirmation req) {
		// TODO Auto-generated method stub
		APIContext context = new APIContext(clientId, clientSecret, production);
		TransactionResult transactionResult = new TransactionResult();

		Agreement agreement = new Agreement();
		agreement.setToken(req.getToken());

		try {
			Agreement activeAgreement = Agreement.execute(context, agreement.getToken());
			System.out.println("Agreement created with ID " + activeAgreement.getId());
			transactionResult.setSuccessMessage("Success");
		} catch (PayPalRESTException e) {
			System.err.println(e.getDetails());
		}
		return transactionResult;

	}

}
