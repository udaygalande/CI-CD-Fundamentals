package com.mindtree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ShoppingcartApplication extends SpringBootServletInitializer {
	
	 @Override
	   protected SpringApplicationBuilder configure (SpringApplicationBuilder builder) {
	       return builder.sources(ShoppingcartApplication.class);
	   }

	public static void main(String[] args) {
		SpringApplication.run(ShoppingcartApplication.class, args);
	}
	
}
