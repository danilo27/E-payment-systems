package pc.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pc.dto.PaymentConfirmationDto;
import pc.dto.StringDto;
import pc.model.Cart;
import pc.model.TransactionResult;
import pc.payments.impl.BitcoinService;

@RestController
@RequestMapping(value = "/bitcoin")
public class BitcoinController {

	@Autowired
	BitcoinService bitcoinService;
	
	@RequestMapping(value = "/prepare",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StringDto> bitcoinRequest(@RequestBody Cart requestDto){
		bitcoinService.prepareTransaction(requestDto);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("http://google.com"));
		return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
	}
	
	@CrossOrigin(origins = "https://sandbox.coingate.com")
	@RequestMapping(value = "/confirm/{cartId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StringDto> bitcoinConfirm(@PathVariable("cartId") long cartId){
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("http://localhost:4200/bitcoin-success"));
		return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
	}

	@CrossOrigin(origins = "https://sandbox.coingate.com")
	@RequestMapping(value = "/cancel/{cartId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StringDto> bitcoinCancel(@PathVariable("cartId") long cartId){
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("http://localhost:4200/bitcoin-error"));
		return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
	}
	
	@CrossOrigin(origins = "https://sandbox.coingate.com")
	@RequestMapping(value = "/callback/{cartId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StringDto> bitcoinCallback(@PathVariable("cartId") long cartId){
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("http://localhost:4200/bitcoin-success"));
		return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
	}

}
