package com.forumTopics.javamav.formTopics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class FormTopicsApplication {

	public static void main(String[] args) { 
		
		SpringApplication.run(FormTopicsApplication.class, args); 
		
	} 
	
	/*@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder(); 
		
	}*/

} 