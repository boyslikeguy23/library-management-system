package com.library.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.entity.Copy;
import com.library.entity.Role;
import com.library.model.ERole;
import com.library.entity.User;
import com.library.jwt.JwtUtils;
import com.library.repository.CopyRepository;
import com.library.repository.RoleRepository;
import com.library.repository.UserRepository;
import com.library.request.LoginRequest;
import com.library.request.SignupRequest;
import com.library.response.JwtResponse;
import com.library.response.MessageResponse;
import com.library.service.MyUserPrincipal;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("users")
@RestController
public class UserController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CopyRepository copyRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	
	@PutMapping("/checkIn/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Copy> checkIn(@PathVariable(name="id") int bookId){
		try {
			Optional<Copy> bookData = copyRepository.findById(bookId);
			//Optional<User> userData = userRepository.findByUsername(username);
			
			if(bookData.isPresent()) {
				Copy book = bookData.get();
				//User user = userData.get();
				User user = userRepository.findByUsername(book.getWhoCheckedOut()).get();
				
				user.checkIn(book);
				
				copyRepository.save(book);
				userRepository.save(user);
				
				return new ResponseEntity<>(book, HttpStatus.OK);
			}
				
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@PutMapping("/checkOut/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Copy> checkOut(@PathVariable(name="id") int id, @RequestParam(required=true) String username){

		Optional<Copy> bookData = copyRepository.findById(id);
		
		Optional<User> userData = userRepository.findByUsername(username);
		
		try {
			if(bookData.isPresent() && userData.isPresent()) {
				User user = userData.get();
				Copy book = bookData.get();
				user.checkOut(book);
				
				copyRepository.save(book);
				userRepository.save(user);
				
				return new ResponseEntity<>(book, HttpStatus.OK);
			}
				
				
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		catch(Exception ex) {
			System.out.println(ex.fillInStackTrace());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/mybooks/{username}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<Copy>> getMyBooks(@PathVariable(name="username") String username){
		
		Optional<User> userData = userRepository.findByUsername(username);
		
		try {
			if(userData.isPresent()) {
				User user = userData.get();
				
				return new ResponseEntity<>(user.getBooks(), HttpStatus.OK);
			}
				
				
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		catch(Exception ex) {
			System.out.println(ex.fillInStackTrace());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@GetMapping("/viewAllUsers")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<User>> viewUsers(){
		try {
			List<User> users = new ArrayList<>();
			userRepository.findAll().forEach(users::add);
			
			if(!users.isEmpty()) {
				return new ResponseEntity<>(users, HttpStatus.OK);
			}
			
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/viewNumOfUsers")
	public ResponseEntity<Integer> getNumOfUsers(){
		try {
			return new ResponseEntity<>(userRepository.findAll().size(), HttpStatus.OK);
			
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		MyUserPrincipal myuserPrincipal = (MyUserPrincipal) authentication.getPrincipal();
		List<String> roles = myuserPrincipal.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
				
		return ResponseEntity.ok(new JwtResponse(jwt,
				myuserPrincipal.getId(),
				myuserPrincipal.getUsername(),
				roles));
				
	}
	
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		
		if(userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body("Error: Username is already taken");
		}
			
		User user = new User(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()));
		
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		
		if(strRoles == null) {
			Role visitorRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(visitorRole);
		}
		else {
			strRoles.forEach(role -> {
					switch(role) {
						case "admin":
							Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
							roles.add(adminRole);
							
							break;
							
						default:
							Role visitorRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
						roles.add(visitorRole);
					}
				});
		}
		
		user.setRoles(roles);
		userRepository.save(user);
		
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	
}
