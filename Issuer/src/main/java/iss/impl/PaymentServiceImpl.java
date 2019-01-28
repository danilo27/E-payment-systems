package iss.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iss.repositories.PaymentRepository;
import iss.services.PaymentService;
import iss.model.Payment;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	private PaymentRepository repo;
	
	@Override
	public Optional<Payment> findOne(Long id) {
		return repo.findById(id);
	}

	@Override
	public List<Payment> findAll() {
		return repo.findAll();
	}

	@Override
	public Payment save(Payment arg) {
		return repo.save(arg);
	}

}
