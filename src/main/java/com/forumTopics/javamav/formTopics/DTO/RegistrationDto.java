package com.forumTopics.javamav.formTopics.DTO; 

public class RegistrationDto { 
	
	private String firstName; 
	private String lastName; 
	private String username; 
	private String tentative_password; 
	private String password_hash; 
	private String email; 
	
	public String getUsername() {
		
		return this.username; 
		
	} 
	
	public void setUsername(String username) {
		
		this.username = username; 
		
	}
	
	public String getFirstName() {
		
		return this.firstName; 
		
	} 
	
	public void setFirstName(String fName) {
		
		this.firstName = fName; 
		
	}
	
	public String getLastName() {
		
		return this.lastName; 
		
	} 
	
	public void setLastName(String lName) {
		
		this.lastName = lName; 
		
	}
	
	public String getEmail() {
		
		return this.email; 
		
	} 
	
	public void setEmail(String email) {
		
		this.email = email; 
		
	} 
	
	public String getTentative_password() {
		
		return this.tentative_password; 
		
	} 
	
	public void setTentative_password(String tentative_password) {
		
		this.tentative_password = tentative_password; 
		
	}
	
	public String getPassword_hash() {
		
		return this.password_hash; 
		
	} 
	
	public void setPassword_hash(String password_hash) {
		
		this.password_hash = password_hash; 
		
	}
	
}