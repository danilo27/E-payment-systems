package pc.payments.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payee;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import pc.dto.PaymentConfirmationDto;
import pc.dto.PaymentRequestDto;
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
	    redirectUrls.setCancelUrl(frontendPort + "/paypal-cancel");
	    redirectUrls.setReturnUrl(frontendPort + "/paypal-success");
	    payment.setRedirectUrls(redirectUrls);
	    	    
	    Payment createdPayment;
	    try {
	        String redirectUrl = "";
	        APIContext context = new APIContext(clientId, clientSecret, production);
	        createdPayment = payment.create(context);
	        if(createdPayment != null){
	            List<Links> links = createdPayment.getLinks();
	            for (Links link: links) {
	                if(link.getRel().equals("approval_url")){
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
	        if(createdPayment != null){
	        	result.setSuccessMessage("Success");
	    	    return result;
	        }
	    } catch (PayPalRESTException e) {
	        System.err.println(e.getDetails());
	    }
		return null;
	}



}
