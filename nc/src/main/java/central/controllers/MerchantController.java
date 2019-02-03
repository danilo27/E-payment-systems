package central.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import central.dto.ApiResponse;
import central.dto.MerchantCredentialsDto;
import central.dto.MerchantToPcDto;
import central.dto.NewMerchantDto;
import central.model.Cart;
import central.model.Magazine;
import central.model.Merchant;
import central.model.SupportedPayments;
import central.repository.MagazineRepository;
import central.repository.MerchantRepository;
import central.repository.SupportedPaymentsRepository;
import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/merchant")
public class MerchantController {

	@Autowired
	private MagazineRepository magazineRepository;
	
	@Autowired
	private MerchantRepository merchantRepository;
	
	//@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	@RequestMapping(value = "/new", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <ApiResponse> newMerchant(@RequestBody NewMerchantDto merchant){
		
		Merchant newMerchant = new Merchant();
		Magazine magazine;
		List<SupportedPayments> supportedPayments = new ArrayList<SupportedPayments>();
		try {
			magazine = magazineRepository.findById(merchant.getMagazineIssn()).orElseThrow(() -> new NotFoundException("magazine with issn " + merchant.getMagazineIssn() + " not found"));
			for (Long spId : merchant.getSupportedPaymentsIds()){
				String fooResourceUrl = "http://localhost:8080/api/payment-types/" + spId;
				RestTemplate rt = new RestTemplate();
				SupportedPayments sp = rt.getForObject(fooResourceUrl, SupportedPayments.class);
				
				supportedPayments.add(sp);
			}
			newMerchant.setMagazine(magazine);			
			
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(new ApiResponse("failed", false), HttpStatus.OK);
		}
		
		String fooResourceUrl = "http://localhost:8081/acqBank/makeMerchantAccount";
		RestTemplate rt = new RestTemplate();
		MerchantCredentialsDto response = rt.getForObject(fooResourceUrl, MerchantCredentialsDto.class);
		
		newMerchant.setMerchantId(response.getMerchantId());
		newMerchant.setMerchantPass(response.getMerchantPassword());
		
		merchantRepository.save(newMerchant);
		magazine.setMerchant(newMerchant);
		magazineRepository.save(magazine);
		
		String fooResourceUrl2 = "http://localhost:8080/api/pc/merchant/new";
		RestTemplate rt2 = new RestTemplate();
		MerchantToPcDto mcp = new MerchantToPcDto();
		mcp.setMerchantId(newMerchant.getMerchantId());
		mcp.setMerchantPass(newMerchant.getMerchantPass());
		mcp.setSupportedPayments(supportedPayments);
		
		ApiResponse response2 = rt2.postForObject(fooResourceUrl2, mcp, ApiResponse.class);
				
		return new ResponseEntity<>(response2, HttpStatus.OK);
	}
}
