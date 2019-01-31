package central.controllers;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
	
	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/all")
	public ResponseEntity<List<Magazine>> all(){
		return new ResponseEntity<List<Magazine>>(magazineRepository.findAll(), HttpStatus.OK);
	    
	}
}
