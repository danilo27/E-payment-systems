package central.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import central.dto.ApiResponse;
import central.model.Merchant;

@RestController
@RequestMapping(value = "/merchant")
public class MerchantController {

	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	@RequestMapping(value = "/new", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <ApiResponse> newMerchant(@RequestBody Merchant merchant){
		
		return new ResponseEntity<>(new ApiResponse("success", true), HttpStatus.OK);
	}
}
