package pc.payments.impl;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.body.MultipartBody;

import pc.dto.PaymentConfirmationDto;
import pc.dto.PaymentRequestDto;
import pc.dto.SubscriptionConfirmation;
import pc.dto.SubscriptionRequest;
import pc.model.PaymentRequest;
import pc.model.TransactionResult;
import pc.payments.IPaymentExtensionPoint;

@Service
public class BitcoinService implements IPaymentExtensionPoint {
	
	public String COINGATE_BASE_URL = "https://api-sandbox.coingate.com/v2";
	public String API_KEY = "eyiSeGsRJresVQPpo2uFYesWRz2KuztKcA5xM3kD";
	
	private Map<String, String> prepareHeaders() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        headers.put("Authorization", "Token " + API_KEY);
        headers.put("User-Agent", "CoinGate Java API Client");
        return headers;
    }
	
	public JSONObject createOrder(
            Double priceAmount,
            String priceCurrency,
            String receiveCurrency,
            String successUrl,
            String cancelUrl
    ) throws UnirestException {

        MultipartBody request = Unirest
                .post(getUri("/orders"))
                .headers(prepareHeaders())
                .field("price_amount", priceAmount)
                .field("price_currency", priceCurrency.toUpperCase())
                .field("receive_currency", receiveCurrency.toUpperCase())
        		.field("cancel_url", cancelUrl)
        		.field("success_url", successUrl);


        HttpResponse<JsonNode> response = request.asJson();
        return response.getBody().getObject();
    }

	public String getUri(String relativeUri) {
        return COINGATE_BASE_URL + relativeUri;
    }
	
	@Override
	public TransactionResult prepareTransaction(PaymentRequest req) {
		TransactionResult result = new TransactionResult();
		result.setRedirectUrl("");
		try {
			JSONObject json = createOrder(5.0, 
											"USD", 
											"USD", 
											"http://localhost:4200/bitcoin-success", 
											"http://localhost:4200/bitcoin-cancel");
			try {
				result.setRedirectUrl((String)json.get("payment_url"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public TransactionResult proceedTransaction(PaymentConfirmationDto req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionResult prepareSubscription(SubscriptionRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionResult proceedSubscription(SubscriptionConfirmation req) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}