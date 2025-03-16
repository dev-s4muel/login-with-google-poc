package com.poc.login_with_google_poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class LoginWithGooglePocApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginWithGooglePocApplication.class, args);
	}

}
