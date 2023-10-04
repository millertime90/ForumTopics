package com.forumTopics.javamav.formTopics.authenticationHandlers; 

import org.springframework.security.core.AuthenticationException; 
import org.springframework.security.web.authentication.AuthenticationFailureHandler; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import java.io.IOException; 

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, 
										HttpServletResponse response,
										AuthenticationException exception)
											throws IOException {
		
		// Set the response type and status 
		response.setContentType("application/json"); 
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); 
		
		// Written JSON response follows 
		response.getWriter().print("{\"success\": false, \"message\": \"" + exception.getMessage() + "\"}"); 
		response.getWriter().flush(); 
		
	}
	
}