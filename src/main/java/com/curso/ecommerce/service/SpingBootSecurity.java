package com.curso.ecommerce.service;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpingBootSecurity {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/administrador/**").hasRole("ADMIN")
						.requestMatchers("/productos/**").hasRole("ADMIN")
				)
				.formLogin(form -> form
						.loginPage("/usuario/login")
						.permitAll().defaultSuccessUrl("/usuario/acceder")
				).logout(LogoutConfigurer::permitAll);

		return http.build();
	}

	@Bean
	public BCryptPasswordEncoder getEnecoder() {
		return new BCryptPasswordEncoder();
	}
}
