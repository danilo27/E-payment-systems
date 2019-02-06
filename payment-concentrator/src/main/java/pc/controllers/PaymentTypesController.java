package pc.controllers;

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
import pc.model.PaymentType;
import pc.model.PaymentTypeField;
import pc.repositories.PaymentTypeFieldRepository;
import pc.repositories.PaymentTypeRepository;

@RestController
@RequestMapping("/payment-types")
public class PaymentTypesController {
	
	@Autowired
	private PaymentTypeRepository paymentTypeRepository;
	
	@Autowired
	private PaymentTypeFieldRepository paymentTypeFieldRepository;
 
	@GetMapping("/all")
	public ResponseEntity<List<PaymentType>> getAll(){
		System.out.println("ok");
		System.out.println(paymentTypeRepository.findAll());
		return new ResponseEntity<List<PaymentType>>(paymentTypeRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PaymentType> getById(@PathVariable Long id) throws NotFoundException{
		return new ResponseEntity<>(paymentTypeRepository.findById(id).orElseThrow(() -> new NotFoundException("payment type with id " + id + " not found")), HttpStatus.OK);
	}
	
	@GetMapping("/fields/{id}")
	public ResponseEntity<List<PaymentTypeField>> fields(@PathVariable String id) throws NotFoundException{
		return new ResponseEntity<List<PaymentTypeField>>(paymentTypeRepository.findByName(id).orElse(null).getFields(), HttpStatus.OK);
	}
}
