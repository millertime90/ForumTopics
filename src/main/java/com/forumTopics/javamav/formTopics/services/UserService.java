package com.forumTopics.javamav.formTopics.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.forumTopics.javamav.formTopics.DTO.RegistrationDto;
import com.forumTopics.javamav.formTopics.exceptions.EmailAlreadyExistsException;
import com.forumTopics.javamav.formTopics.exceptions.PasswordMismatchException;
import com.forumTopics.javamav.formTopics.exceptions.PasswordValidationException;
import com.forumTopics.javamav.formTopics.exceptions.UsernameAlreadyExistsException;
import com.forumTopics.javamav.formTopics.models.User;
import com.forumTopics.javamav.formTopics.repositories.UserRepository;

@Service
public class UserService { 
	
	@Autowired
	private UserRepository userRepository; 
	@Autowired
	private BCryptPasswordEncoder passwordEncoder; 
	
	public User findByUsername(String username) {
		
		return this.userRepository.findByUsername(username); 
		
	}
	
	public User register(RegistrationDto registrationDto) {
		
		System.out.println("Received registration request: {}\n"); 
		System.out.println(registrationDto); 
		System.out.println(registrationDto.getPassword_hash()); 
		System.out.println(registrationDto.getEmail()); 
		System.out.println(registrationDto.getFirstName()); 
		System.out.println(registrationDto.getLastName()); 
		
		// TODO: Implement user registration logic 
		// For now, just create a simple user, but this will change! 
		
		// Check if the password is null or empty 
		if(registrationDto.getPassword_hash() == null || registrationDto.getPassword_hash().isEmpty()) {
			
			throw new IllegalArgumentException("Password cannot be null or empty."); 
			
		}
		
		// Check if username already exists 
		if(this.userRepository.existsByUsername(registrationDto.getUsername())) {
			
			throw new UsernameAlreadyExistsException("Username already taken."); 
			
		} 
		
		// Check if email already exists 
		if(this.userRepository.existsByEmail(registrationDto.getEmail())) {
			
			throw new EmailAlreadyExistsException("An account with this email already exists."); 
			
		} 
		
		// Validate password requirements sent by user 
		this.validatePassword(registrationDto.getPassword_hash()); 
		
		// Validate password mismatch test 
		if(this.validatePasswordMismatch(registrationDto.getTentative_password(), registrationDto.getPassword_hash())) {
			
			throw new PasswordMismatchException("Password and confirm password fields do not match."); 
			
		}
		
		// Hash the password 
		String hashedPassword = passwordEncoder.encode(registrationDto.getPassword_hash()); 
		
		User newUser = new User(); 
		newUser.setFirstName(registrationDto.getFirstName()); 
		newUser.setLastName(registrationDto.getLastName()); 
		newUser.setEmail(registrationDto.getEmail()); 
		newUser.setUsername(registrationDto.getUsername()); 
		newUser.setPasswordHash(hashedPassword); // user hashed password 
		// ... other fields ... 
		
		return this.userRepository.save(newUser); 
		
	} 
	
	private void validatePassword(String password) {
		
		System.out.println("`validatePassword` invoked*"); 
		
		boolean lengthCheck = password.length() >= 8; 
		boolean specialCharacterCheck = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\/,.<>\\/?].*"); 
		boolean numericCheck = password.matches(".*[0-9].*"); 
		
		System.out.println(!lengthCheck); 
		System.out.println(!specialCharacterCheck); 
		System.out.println(!numericCheck); 
		
		if(!lengthCheck || !specialCharacterCheck || !numericCheck) {
			
			System.out.println("`PasswordValidationException` thrown*"); 
			throw new PasswordValidationException("Password doesn't meet requirements"); 
			
		}
		
	} 
	
	private boolean validatePasswordMismatch(String tentative_password, String password_hash) {
		
		return !tentative_password.equals(password_hash); 
		
	}
	
}