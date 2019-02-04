package pc.controllers;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;
import pc.model.Cart;
import pc.model.PaymentType;
import pc.repositories.CartRepository;
import pc.repositories.MerchantRepository;
import pc.repositories.PaymentTypeRepository;

@RestController
@RequestMapping("/payment-types")
public class PaymentTypesController {
	
	@Autowired
	private PaymentTypeRepository paymentTypeRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private MerchantRepository merchantRepository;
	
	@GetMapping("/all")
	public ResponseEntity<List<PaymentType>> getAll(){
		return new ResponseEntity<List<PaymentType>>(paymentTypeRepository.findAll(), HttpStatus.OK);
	}
/*
	@GetMapping("/{id}")
	public ResponseEntity<PaymentType> getById(@PathVariable Long id) throws NotFoundException{
		return new ResponseEntity<>(paymentTypeRepository.findById(id).orElseThrow(() -> new NotFoundException("payment type with id " + id + " not found")), HttpStatus.OK);
	}*/
	
	@GetMapping("/{cartId}")
	public ResponseEntity<List<PaymentType>> getPaymentTypes(@PathVariable String cartId) throws NotFoundException{
		System.out.println("[PC] getPaymentTypes, cartId: " + cartId);
		Cart c = cartRepository.findById(Long.parseLong(cartId)).orElseThrow(() -> new NotFoundException("cart with id " + cartId + " not found"));
		if(c!=null){
			return new ResponseEntity<List<PaymentType>>(merchantRepository.findByMerchantId(c.getMerchantId()).getSupportedPayments(), HttpStatus.OK);
		}
	    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	    
	}
	

}
