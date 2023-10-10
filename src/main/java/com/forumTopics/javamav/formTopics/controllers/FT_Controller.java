package com.forumTopics.javamav.formTopics.controllers;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.forumTopics.javamav.formTopics.DTO.RegistrationDto;
import com.forumTopics.javamav.formTopics.exceptions.EmailAlreadyExistsException;
import com.forumTopics.javamav.formTopics.exceptions.PasswordMismatchException;
import com.forumTopics.javamav.formTopics.exceptions.PasswordValidationException;
import com.forumTopics.javamav.formTopics.exceptions.UsernameAlreadyExistsException;
import com.forumTopics.javamav.formTopics.models.User;
import com.forumTopics.javamav.formTopics.repositories.UserRepository;
import com.forumTopics.javamav.formTopics.services.UserService;

@RestController
@RequestMapping("/index")
@CrossOrigin(origins = "http://localhost:8083")
public class FT_Controller {
	
	@Autowired
	private UserRepository userRepository; 
	@Autowired
	private UserService userService; 
	
	@GetMapping
	public ModelAndView displayIndex() {
		
		ModelAndView modelAndView = new ModelAndView("index"); 
		return modelAndView; 
		
	} 
	
	@GetMapping("/myprofile")
	public ModelAndView displayMyProfile(Principal principal) {
		
		ModelAndView modelAndView = new ModelAndView("myprofile"); 
		User user = userService.findByUsername(principal.getName()); 
		
		modelAndView.addObject("user", user); 
		
		return modelAndView; 
		
	}
	
	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<User> register(@RequestBody RegistrationDto registrationDto) {
		
		User registeredUser = this.userService.register(registrationDto); 
		return new ResponseEntity<>(registeredUser, HttpStatus.CREATED); 
		
	} 
	
	@GetMapping("/endpointtest")
	public List<User> fetchAllUsers() {
		
		return this.userRepository.findAll(); 
		
	} 
	
	@ExceptionHandler(UsernameAlreadyExistsException.class)
	public ResponseEntity<String> handleUsernameAlreadyExists(UsernameAlreadyExistsException ex) {
		
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST); 
		
	} 
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<String> emailAlreadyExists(EmailAlreadyExistsException ex) {
		
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST); 
		
	} 
	
	@ExceptionHandler(PasswordValidationException.class)
	public ResponseEntity<Object> handlePasswordValidationException(PasswordValidationException ex) {
		
		Map<String, Object> body = new LinkedHashMap<>(); 
		body.put("timestamp", LocalDateTime.now()); 
		body.put("message", ex.getMessage()); 
		body.put("exceptionType", ex.getClass().getSimpleName());
		
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST); 
		
	} 
	
	@ExceptionHandler(PasswordMismatchException.class)
	public ResponseEntity<Object> handlePasswordMismatchException(PasswordMismatchException ex) {
		
		Map<String, Object> body = new LinkedHashMap<>(); 
		body.put("timestamp", LocalDateTime.now()); 
		body.put("message", ex.getMessage()); 
		body.put("exceptionType", ex.getClass().getSimpleName()); 
		
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST); 
		
	} 
	
} 