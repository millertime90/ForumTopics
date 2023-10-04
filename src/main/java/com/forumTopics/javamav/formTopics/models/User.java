package com.forumTopics.javamav.formTopics.models; 

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email; 

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Column(name = "first_name")
	private String firstName; 
	
	@Column(name = "last_name")
	private String lastName; 
	
	@Email
	@Column(name = "email")
	private String email; 
	
	@Column(name = "username")
	private String username; 
	
	@Column(name = "password_hash")
	private String passwordHash; 
	
	// other fields, getters, setters, and etc. will follow in further development 
	
	public Long getId() {
		
		return this.id; 
		
	} 
	
	public void setId(Long id) {
		
		this.id = id; 
		
	} 
	
	public String getFirstName() {
		
		return this.firstName; 
		
	} 
	
	public void setFirstName(String firstName) {
		
		this.firstName = firstName; 
		
	} 
	
	public String getLastName() {
		
		return this.lastName; 
		
	} 
	
	public void setLastName(String lastName) {
		
		this.lastName = lastName; 
		
	} 
	
	public String getEmail() {
		
		return this.email; 
		
	} 
	
	public void setEmail(String email) {
		
		this.email = email; 
		
	} 
	
	public String getUsername() {
		
		return this.username; 
		
	}
	
	public void setUsername(String username) {
		
		this.username = username; 
		
	} 
	
	public String getPasswordHash() {
		
		return this.passwordHash; 
		
	}
	
	public void setPasswordHash(String passwordHash) {
		
		this.passwordHash = passwordHash; 
		
	}
	
} 