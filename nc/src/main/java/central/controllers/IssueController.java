package central.controllers;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import central.model.Cart;
import central.model.Issue;
import central.repository.IssueRepository;
import central.repository.MagazineRepository;


@RestController
@RequestMapping(value = "/nc/issue")
public class IssueController {
	@Autowired
	private MagazineRepository magazineRepository;
	
	@Autowired
	private IssueRepository issueRepository;
	
	
	
	@GetMapping("/allIssuesForMagazine/{issn}")
	public ResponseEntity<List<Issue>> all(@PathVariable String issn){
		return new ResponseEntity<List<Issue>>(issueRepository.findByMagazineIssn(issn), HttpStatus.OK);
	}
	
	@GetMapping("/getOne/{id}")
	public ResponseEntity<Issue> getOne(@PathVariable String id){
		return new ResponseEntity<Issue>(issueRepository.findById(Long.parseLong(id)).orElse(null), HttpStatus.OK);
	}
	
	 
}
