package com.terry.security.demo.springsecurity6demo.configuration;

import com.terry.security.demo.springsecurity6demo.user.filter.JwtTokenFilter;
import com.terry.security.demo.springsecurity6demo.user.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.List;

@Configuration
@EnableWebSecurity
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
