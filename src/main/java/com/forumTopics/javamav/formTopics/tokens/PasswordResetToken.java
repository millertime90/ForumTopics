package com.forumTopics.javamav.formTopics.tokens;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.forumTopics.javamav.formTopics.models.User;

@Entity
public class PasswordResetToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; 
	
	private String token; 
	
	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user; 
	
	private LocalDateTime expiryDate; 
	
	public PasswordResetToken(User user) {
		
		this.user = user; 
		
	}
	
	public Long getId() {
		
		return this.id; 
		
	} 
	
	public void setId(Long id) {
		
		this.id = id; 
		
	} 
	
	public String getToken() {
		
		return this.token; 
		
	} 
	
	public void setToken(String token) {
		
		this.token = token; 
		
	} 
	
	public LocalDateTime getExpiryDate() {
		
		return this.expiryDate; 
		
	} 
	
	public void setExpiryDate(LocalDateTime expiryDate) {
		
		this.expiryDate = expiryDate; 
		
	}
	
}