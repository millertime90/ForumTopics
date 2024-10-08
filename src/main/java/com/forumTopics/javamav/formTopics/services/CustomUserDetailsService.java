package com.forumTopics.javamav.formTopics.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service; 
import java.util.ArrayList; 
import com.forumTopics.javamav.formTopics.models.User;
import com.forumTopics.javamav.formTopics.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository; 
	
	@SuppressWarnings("unused")
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("`loadUserByUsername` by class `CustomUserDetailsService` is invoked."); 
		System.out.println("Passed `username` parameter: " + username); 
		
		// Fetch the user from the database 
		User user = userRepository.findByUsername(username); 
		
//		System.out.println("Username: " + user.getUsername()); 
//		System.out.println("Password Hash: " + user.getPasswordHash()); 
		System.out.println(user == null); 
		
		if(user == null) {
			
			System.out.println("UsernameNotFoundException may be getting thrown. Might have to send a JSON response back to the client with an notated exception handler (ResponseEntity) method in the controller class"); 
			throw new UsernameNotFoundException("User not found with username " + username); 
			
		} 
		
		// Convert your user entity to Spring Security's UserDetails 
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPasswordHash(), new ArrayList<>()); 
		
	}
	
}