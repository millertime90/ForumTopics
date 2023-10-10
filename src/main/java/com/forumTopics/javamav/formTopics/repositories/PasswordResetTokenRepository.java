package com.forumTopics.javamav.formTopics.repositories; 

import org.springframework.data.jpa.repository.JpaRepository;
import com.forumTopics.javamav.formTopics.models.User;
import com.forumTopics.javamav.formTopics.tokens.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
	
	PasswordResetToken findByToken(String token); 
	PasswordResetToken findByUser(User user); 
	
}