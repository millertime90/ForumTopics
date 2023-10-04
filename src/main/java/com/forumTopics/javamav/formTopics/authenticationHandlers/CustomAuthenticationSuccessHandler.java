package com.forumTopics.javamav.formTopics.authenticationHandlers; 

import org.springframework.security.core.Authentication; 
import org.springframework.security.web.authentication.AuthenticationSuccessHandler; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import java.io.IOException; 

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, 
										HttpServletResponse response, 
										Authentication authentication) 
											throws IOException {
		
		// Set the response type and status 
		response.setContentType("application/json"); 
		response.setStatus(HttpServletResponse.SC_OK); 
		
		// Written JSON response follows 
		response.getWriter().print("{\"success\": true, \"message\": \"Authentication successful!\"}"); 
		response.getWriter().flush(); 
		
	}
	
} 