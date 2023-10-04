package com.forumTopics.javamav.formTopics.models; 

import javax.persistence.*; 

@Entity
@Table(name = "useroauth")
public class UserOAuth {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user; 
	
	@Column(name = "provider_id")
	private String providerId; 
	
	@Column(name = "provider_user_id")
	private String providerUserId; 
	
	// other fields, getters, and setters, and etc. will follow in further development 
	
} 