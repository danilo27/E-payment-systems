package central.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import central.dto.ApiResponse;
import central.dto.JwtAuthenticationResponse;
import central.dto.LoginRequest;
import central.model.User;
import central.repository.RoleRepository;
import central.repository.UserRepository;
import central.security.JwtTokenProvider;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		logger.info("User: " + loginRequest.getUsername() + " Trying to Log in");
		User user = userRepository.findByUsername(loginRequest.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

		logger.info("User: " + loginRequest.getUsername() + " Logged in");
		String jwt = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));

	}

	@GetMapping("/logout")
	public ResponseEntity<ApiResponse> logout() {
		// SecurityContextHolder.getContext().setAuthentication(null);
		// ServletRequestAttributes attr = (ServletRequestAttributes)
		// RequestContextHolder.currentRequestAttributes();
		// attr.getRequest().getSession().invalidate();
		logger.info("User: " + SecurityContextHolder.getContext().getAuthentication().getName() + " Logged out");
		return ResponseEntity.ok(new ApiResponse("successfully logged out", true));
	}
}
