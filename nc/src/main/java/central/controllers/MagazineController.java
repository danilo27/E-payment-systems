package central.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import central.model.Magazine;
import central.repository.MagazineRepository;

@RestController
@RequestMapping(value = "/nc/magazine")
public class MagazineController {
	
	@Autowired
	private MagazineRepository magazineRepository;
	
	@GetMapping("/all")
	public ResponseEntity<List<Magazine>> all(){
		return new ResponseEntity<List<Magazine>>(magazineRepository.findAll(), HttpStatus.OK);
	    
	}
	
	@GetMapping("/withoutMerchant")
	public ResponseEntity<List<Magazine>> withoutMerchant(){
		List<Magazine> magazines = new ArrayList<Magazine>();
		for(Magazine m : magazineRepository.findAll()){
			if(m.getMerchant()==null){
				System.out.println("magazine: "+ m.getName());
				magazines.add(m);
			}
		}
		return new ResponseEntity<List<Magazine>>(magazines, HttpStatus.OK);
	    
	}
}
