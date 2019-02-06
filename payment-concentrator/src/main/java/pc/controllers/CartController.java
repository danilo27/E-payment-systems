package pc.controllers;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;
import pc.model.Cart;
import pc.repositories.CartRepository;

@RestController
@RequestMapping(value = "/pc/cart")
public class CartController {

	@Autowired
	private CartRepository cartRepository;
	
	@GetMapping("/{token}")
	public ResponseEntity<Cart> getCart(@PathVariable String token) throws URISyntaxException, NumberFormatException, NotFoundException{
		System.out.println("[PC] getCart, token: " + token);
	    return new ResponseEntity<Cart>(cartRepository.findById(Long.parseLong(token)).orElseThrow(() -> new NotFoundException("cart with id " + token + " not found")), HttpStatus.OK);
	    
	}
}
