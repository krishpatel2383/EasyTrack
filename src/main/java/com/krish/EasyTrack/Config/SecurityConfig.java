package com.krish.EasyTrack.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

	@Bean
	UserDetailsService userDetailsService(PasswordEncoder encoder) {
		UserDetails employee = User.withUsername("employee").password(encoder.encode("employee")).roles("EMPYOLEE")
				.build();
		UserDetails admin = User.withUsername("admin").password(encoder.encode("admin")).roles("ADMIN", "EMPLOYEE")
				.build();

		return new InMemoryUserDetailsManager(employee, admin);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
