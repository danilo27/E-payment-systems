package pc.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pc.dto.StringDto;
import pc.model.TransactionResult;
import pc.payments.impl.BitcoinService;

@RestController
@RequestMapping(value = "/bitcoin")
public class BitcoinController {
/*
	@Autowired
	BitcoinService bitcoinService;
	
	@RequestMapping(value = "/prepare",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StringDto> bitcoinRequest(){
		TransactionResult result = bitcoinService.prepareTransaction(null);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create(result.getRedirectUrl()));
		return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
	}
	*/

}
