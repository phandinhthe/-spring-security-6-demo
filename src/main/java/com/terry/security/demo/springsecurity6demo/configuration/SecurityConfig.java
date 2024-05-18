package com.terry.security.demo.springsecurity6demo.configuration;

import com.terry.security.demo.springsecurity6demo.user.filter.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	@ConditionalOnProperty(name = "terry.authorization.configuration.default", havingValue = "true")
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				.build();
	}

	@Autowired
	JwtTokenFilter filter;

	@ConditionalOnProperty(name = "terry.authorization.configuration.jwt", havingValue = "true")
	@Bean
	public SecurityFilterChain filterChainJwt(HttpSecurity http) throws Exception {
		return http
				.csrf(a -> a.ignoringRequestMatchers("/api/login"))
				.authorizeHttpRequests(authorize -> authorize.requestMatchers("/api/login").permitAll())
				.authorizeHttpRequests(authorize -> authorize.requestMatchers("/api/v1/**").authenticated())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
}
