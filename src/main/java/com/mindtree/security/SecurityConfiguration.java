package com.mindtree.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource dataSource;
	
	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder){
		
		authenticationManagerBuilder.jdbcAuthentication().dataSource(dataSource).withDefaultSchema().withUser("uday").password("password").roles("USER");)
		
	}
}
