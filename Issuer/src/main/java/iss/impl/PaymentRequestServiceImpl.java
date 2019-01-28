package iss.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iss.repositories.PaymentRequestRepository;
import iss.services.PaymentRequestService;
import iss.model.PaymentRequest;

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

	@Override
	public PaymentRequest findByToken(String token) {
		return repo.findByToken(token);
	}
	
	
}
