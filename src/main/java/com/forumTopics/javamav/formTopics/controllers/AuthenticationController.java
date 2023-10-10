package com.forumTopics.javamav.formTopics.controllers;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.forumTopics.javamav.formTopics.models.User;
import com.forumTopics.javamav.formTopics.repositories.UserRepository;
import com.forumTopics.javamav.formTopics.services.PasswordResetService;

@RestController
@RequestMapping("/index/api/auth")
public class AuthenticationController {
	
	@Autowired
	UserRepository userRepository; 
	
	@Autowired
	PasswordResetService passwordResetService; 
	
	@PostMapping("/forgot-password")
	public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> request) {
		
		String email = request.get("email"); 
		User user = userRepository.findByEmail(email); 
		
		if(user == null) {
			
			return ResponseEntity.badRequest().body("Email not registered."); 
			
		} 
		else {
			
			passwordResetService.createPasswordResetTokenForUser(user); 
			return ResponseEntity.ok("Reset link sent to your email."); 
			
		}
		
	}
	
}