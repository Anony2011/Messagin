package com.wissam.messaging.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import com.wissam.messaging.AuthenticationFilter;
import com.wissam.messaging.LoginFilter;
import com.wissam.messaging.service.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
@EnableEncryptableProperties
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailServiceImpl userDetailsService; 

	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
		 http.csrf().disable().cors().disable().authorizeRequests()
		  .antMatchers(HttpMethod.POST, "/login").permitAll()
		  .antMatchers(HttpMethod.GET, "/message/*").permitAll()
	        .anyRequest().authenticated()
	        .and()
	        // Filter for the login requests
	        .addFilterBefore(new LoginFilter("/login", authenticationManager()),
	                UsernamePasswordAuthenticationFilter.class)
	        // Filter for other requests to check JWT in header
	        .addFilterBefore(new AuthenticationFilter(),
	                UsernamePasswordAuthenticationFilter.class);
	  }
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
		
		
	}
	


}