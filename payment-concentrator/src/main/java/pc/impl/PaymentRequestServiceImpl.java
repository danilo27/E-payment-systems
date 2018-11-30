package pc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pc.repositories.PaymentRequestRepository;
import pc.services.PaymentRequestService;

@Service
public class PaymentRequestServiceImpl implements PaymentRequestService{
	@Autowired
	private PaymentRequestRepository repo;
	
	
}
