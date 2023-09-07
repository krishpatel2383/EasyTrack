package com.krish.EasyTrack.Config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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

//	@// @formatter:off
 
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.
			authorizeHttpRequests((auth)->auth
				.requestMatchers("/employees/**","/reports/**").hasRole("ADMIN")
				.requestMatchers("/attendance/**").hasAnyRole("EMPLOYEE","ADMIN")
				.anyRequest().authenticated())
				.formLogin(withDefaults());
		return http.build();
	}
	
// @formatter:on

}
