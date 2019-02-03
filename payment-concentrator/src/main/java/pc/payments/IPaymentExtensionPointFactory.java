package pc.payments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import pc.model.PaymentType;
import pc.payments.card.CardService;
import pc.payments.impl.BitcoinService;
import pc.payments.impl.PayPalService;
import pc.repositories.PaymentTypeRepository;

@Service
public class IPaymentExtensionPointFactory {

	@Autowired
	private PaymentTypeRepository paymentTypeRepository;

	public IPaymentExtensionPoint getPaymentMethod(String paymentName) throws NotFoundException {
		PaymentType pt = paymentTypeRepository.findByName(paymentName)
				.orElseThrow(() -> new NotFoundException("Payment type with name " + paymentName + " not found"));

		if (pt == null) {
			return null;
		}
		if (pt.getName().equalsIgnoreCase("PAYPAL")) {
			return new PayPalService();

		} else if (pt.getName().equalsIgnoreCase("BITCOIN")) {
			return new BitcoinService();

		} else if (pt.getName().equalsIgnoreCase("CARD")) {
			return new CardService();
		}

		return null;
	}
}
