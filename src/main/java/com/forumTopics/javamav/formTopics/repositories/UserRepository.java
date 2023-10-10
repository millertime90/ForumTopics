package com.forumTopics.javamav.formTopics.repositories; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.forumTopics.javamav.formTopics.models.User; 

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	// Custom query methods if needed 
	boolean existsByUsername(String username); 
	boolean existsByEmail(String email); 
	User findByUsername(String username);
	User findByEmail(String email); 
	
} 