package com.forumTopics.javamav.formTopics.config; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity; 
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity; 
import org.springframework.security.core.userdetails.User; 
import org.springframework.security.core.userdetails.UserDetails; 
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager; 
import org.springframework.security.web.SecurityFilterChain; 
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.forumTopics.javamav.formTopics.authenticationHandlers.CustomAuthenticationFailureHandler;
import com.forumTopics.javamav.formTopics.authenticationHandlers.CustomAuthenticationSuccessHandler;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter; 

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService; 
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()); 
		
	} 
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
        
		// Temporarily disabling CSRF protection 
		// http.csrf().disable(); 
		
		// Setting up authorization requests 
		http.authorizeRequests(requests -> requests
                .antMatchers("/css/**", "/js/**", "/framework/**", "/img/**").permitAll()
                .antMatchers("/", "index").permitAll()
                .antMatchers("/index/register").permitAll()
                .anyRequest()
                .authenticated())
                .formLogin(login -> login
                        .loginPage("/index")
                        .loginProcessingUrl("/index/login")
                        .successHandler(new CustomAuthenticationSuccessHandler())
                        .failureHandler(new CustomAuthenticationFailureHandler())
                        .usernameParameter("loginForm_username")
                        .passwordParameter("login_password")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/index/logout")
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/index")); 
		
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder(); 
		
	} 
	
	/*@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder(); 
		
	}*/ 
	
} 