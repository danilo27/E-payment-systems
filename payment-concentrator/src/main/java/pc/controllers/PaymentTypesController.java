package pc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;
import pc.model.PaymentType;
import pc.repositories.PaymentTypeRepository;

@RestController
@RequestMapping("/payment-types")
public class PaymentTypesController {
	
	@Autowired
	private PaymentTypeRepository paymentTypeRepository;
	
	@GetMapping("/all")
	public ResponseEntity<List<PaymentType>> getAll(){
		return new ResponseEntity<List<PaymentType>>(paymentTypeRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PaymentType> getById(@PathVariable Long id) throws NotFoundException{
		return new ResponseEntity<>(paymentTypeRepository.findById(id).orElseThrow(() -> new NotFoundException("payment type with id " + id + " not found")), HttpStatus.OK);
	}
}
