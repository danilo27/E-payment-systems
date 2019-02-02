package central.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import central.model.SupportedPayments;
import central.repository.SupportedPaymentsRepository;

@RestController
@RequestMapping(value = "/nc/supported-payments")
public class SupportedPaymentsController {

	@Autowired
	private SupportedPaymentsRepository supportedPaymentsRepository;
	
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	@GetMapping("/all")
	public ResponseEntity<List<SupportedPayments>> all(){
		return new ResponseEntity<List<SupportedPayments>>(supportedPaymentsRepository.findAll(), HttpStatus.OK);
	    
	}
}
