package acq.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acq.model.Payment;
import acq.repositories.PaymentRepository;
import acq.services.PaymentService;

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

	@Override
	public List<Payment> findByMessage(String string) {
		return repo.findByMessage(string);
	}

}
