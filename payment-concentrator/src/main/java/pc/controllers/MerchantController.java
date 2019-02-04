package pc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pc.dto.ApiResponse;
import pc.model.Merchant;
import pc.model.PaymentType;
import pc.repositories.MerchantRepository;

@RestController
@RequestMapping("/pc/merchant")
public class MerchantController {

	@Autowired
	private MerchantRepository merchantRepository;
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ResponseEntity <ApiResponse> newMerchant(@RequestBody Merchant merchant){
		System.out.println("[PC] New Merchant: " + merchant.toString());
		merchantRepository.save(merchant);

		return new ResponseEntity<>(new ApiResponse("success", true), HttpStatus.OK);
	}
}
