package com.dmitriyg.authMeetsCrud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		return http
				.authorizeHttpRequests(requests -> requests
						//.anyRequest().authenticated())
						.requestMatchers("/*").permitAll()
						.requestMatchers("/user/**").hasAuthority("USER")
						.requestMatchers("/moderator/**").hasAuthority("MODERATOR")
						.requestMatchers("/admin/**").hasAuthority("ADMIN"))
				
				.formLogin(requests -> requests.permitAll())

				.logout(requests -> requests.permitAll())

				/*
				.exceptionHandling(requests -> requests
						.accessDeniedPage("/access-denied")) */
				
				.build();
	}
	
	@Bean
	public AuthenticationManager authManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
		  .userDetailsService(userDetailsService)
		  .passwordEncoder(bCryptPasswordEncoder)
		  .and()
		  .build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	 
	

}
