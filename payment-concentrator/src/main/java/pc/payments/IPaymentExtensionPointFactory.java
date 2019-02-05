package pc.payments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import pc.model.PaymentType;
import pc.repositories.PaymentTypeRepository;

@Service
public class IPaymentExtensionPointFactory {

	@Autowired
	private PaymentTypeRepository paymentTypeRepository;

	public IPaymentExtensionPoint getPaymentMethod(String paymentName) throws NotFoundException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		PaymentType pt = paymentTypeRepository.findByName(paymentName)
				.orElseThrow(() -> new NotFoundException("Payment type with name " + paymentName + " not found"));

		if (pt == null) {
			return null;
		}
		
		return (IPaymentExtensionPoint) Class.forName("pc.payments.impl." + paymentName + "Service").newInstance();
	}
}
