package com.app.controller;

import com.app.model.Cart;
import com.app.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.config.JwtProvider;
import com.app.exception.UserException;
import com.app.model.User;
import com.app.repository.UserRepository;
import com.app.request.LoginRequest;
import com.app.response.AuthResponse;
import com.app.service.CustomUserServiceImplementation;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private CustomUserServiceImplementation userServiceDetail;
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user)throws UserException
	{
		String email = user.getEmail();
		String password = user.getPassword();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		
		User isExistUser = userRepository.findByEmail(email);
		
		if(isExistUser!=null)
		{
			throw new UserException("Email is already used with another account!!!");
		}
		
		User createdUser = new User();
		createdUser.setEmail(email);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setFirstName(firstName);
		createdUser.setLastName(lastName);
		
		User savedUser = userRepository.save(createdUser);
		cartService.createCart(savedUser);

		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtProvider.generatetoken(authentication);
		
		AuthResponse authResponse = new AuthResponse(jwt,"SignUp Successfully!!");
//		authResponse.setJwt(jwt);
//		authResponse.setMessage("SignUp Successfully!!");
		
		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
		
	}
	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest request) throws UserException
	{
		String username = request.getEmail();
		String password = request.getPassword();
		
		Authentication authentication = authenticate(username,password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generatetoken(authentication);
		AuthResponse authResponse = new AuthResponse(jwt, "SignIn Successfully!!");
		
		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
				
	}


	private Authentication authenticate(String username, String password) {
		UserDetails userDetails = userServiceDetail.loadUserByUsername(username);
		
		if(userDetails==null)
		{
			throw new BadCredentialsException("Invalid Username!!");
		}
		
		if(!passwordEncoder.matches(password,userDetails.getPassword()))
		{
			throw new BadCredentialsException("Invalid Password!!");
		}
		
		return new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
	}

}
