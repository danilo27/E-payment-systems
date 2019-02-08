package central.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import central.model.Magazine;
import central.model.PaymentType;
import central.model.PaymentTypeField;
import central.repository.MagazineRepository;
import javassist.NotFoundException;
 

@RestController
@RequestMapping(value = "/nc/supported-payments")
public class SupportedPaymentsController {
	
	@Value("${pc.url}")
	private String pcUrl;
	
	@Autowired
	private MagazineRepository magazineRepository;
	
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	@GetMapping("/all")
	public ResponseEntity<List<PaymentType>> all(){	
		String fooResourceUrl = pcUrl+"/api/payment-types/all";
		RestTemplate rt = new RestTemplate();
		ResponseEntity response = rt.getForEntity(fooResourceUrl, List.class);	
		return new ResponseEntity<List<PaymentType>>((List<PaymentType>)response.getBody(), HttpStatus.OK);
	    
	}
	
	
	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/{magazineIssn}")
	public ResponseEntity<List<PaymentType>> getByMagazineIssn(@PathVariable String magazineIssn){	
		Magazine magazine = magazineRepository.findOneByIssn(magazineIssn);
		if (magazine.getMerchant() == null)
			return new ResponseEntity<>(null, HttpStatus.OK);

		String fooResourceUrl = pcUrl+"/api/payment-types/byMerchant/" + magazine.getMerchant().getMerchantId();
		RestTemplate rt = new RestTemplate();
		ResponseEntity response = rt.getForEntity(fooResourceUrl, List.class);	
		return new ResponseEntity<List<PaymentType>>((List<PaymentType>)response.getBody(), HttpStatus.OK);
	    
	}
	
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	@GetMapping("/fields/{id}")
	public ResponseEntity<List<PaymentTypeField>> fields(@PathVariable String id) throws NotFoundException{
		RestTemplate rt = new RestTemplate();
		ResponseEntity response = rt.getForEntity(pcUrl+"/api/payment-types/fields/"+id, List.class);
		return response;
	}
}
