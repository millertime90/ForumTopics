package com.forumTopics.javamav.formTopics.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.forumTopics.javamav.formTopics.models.User;
import com.forumTopics.javamav.formTopics.repositories.PasswordResetTokenRepository;
import com.forumTopics.javamav.formTopics.tokens.PasswordResetToken;

@Service
public class PasswordResetService {
	
	@Autowired
	PasswordResetTokenRepository tokenRepository; 
	
	@Autowired
	EmailService emailService; 
	
	public void createPasswordResetTokenForUser(User user) {
		
		PasswordResetToken token = new PasswordResetToken(user); 
		tokenRepository.save(token); 
		
		String resetUrl = "https://localhost:8083/reset-password?token=" + token.getToken(); 
		emailService.sendSimpleMessage(user.getEmail(), "Reset Password", "Click the link to reset password: " + resetUrl); 
		
	} 
	
	public boolean validatePasswordResetToken(String token) {
		
		PasswordResetToken passToken = tokenRepository.findByToken(token); 
		if(passToken == null) {
			
			return false; 
			
		} 
		else { return true; } 
		
	}
	
}