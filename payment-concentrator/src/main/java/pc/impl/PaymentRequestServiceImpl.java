package pc.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pc.model.PaymentRequest;
import pc.repositories.PaymentRequestRepository;
import pc.services.PaymentRequestService;

@Service
public class PaymentRequestServiceImpl implements PaymentRequestService{
	@Autowired
	private PaymentRequestRepository repo;

	@Override
	public Optional<PaymentRequest> findOne(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public List<PaymentRequest> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	 

	@Override
	public PaymentRequest save(PaymentRequest arg) {
		// TODO Auto-generated method stub
		return repo.save(arg);
	}
	
	
}
