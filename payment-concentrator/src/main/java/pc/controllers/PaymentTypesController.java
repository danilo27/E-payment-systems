package pc.controllers;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javassist.NotFoundException;
import pc.model.Cart;
import pc.model.PaymentType;
//<<<<<<< HEAD
import pc.model.PaymentTypeField;
import pc.repositories.PaymentTypeFieldRepository;
//=======
import pc.repositories.CartRepository;
import pc.repositories.MerchantRepository;
//>>>>>>> 0c5dc2f1c2e6ae9c0ee9d2b8e5544a473118b607
import pc.repositories.PaymentTypeRepository;

@RestController
@RequestMapping("/payment-types")
public class PaymentTypesController {
	
	@Autowired
	private PaymentTypeRepository paymentTypeRepository;
	
	@Autowired
//<<<<<<< HEAD
	private PaymentTypeFieldRepository paymentTypeFieldRepository;
 
//=======
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private MerchantRepository merchantRepository;
	
//>>>>>>> 0c5dc2f1c2e6ae9c0ee9d2b8e5544a473118b607
	@GetMapping("/all")
	public ResponseEntity<List<PaymentType>> getAll(){
		System.out.println("ok");
		System.out.println(paymentTypeRepository.findAll());
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
	
//<<<<<<< HEAD
	@GetMapping("/fields/{id}")
	public ResponseEntity<List<PaymentTypeField>> fields(@PathVariable String id) throws NotFoundException{
		return new ResponseEntity<List<PaymentTypeField>>(paymentTypeRepository.findByName(id).orElse(null).getFields(), HttpStatus.OK);
	}
//=======

//>>>>>>> 0c5dc2f1c2e6ae9c0ee9d2b8e5544a473118b607
}
