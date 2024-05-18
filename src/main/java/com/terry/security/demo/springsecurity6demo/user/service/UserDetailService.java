package com.terry.security.demo.springsecurity6demo.user.service;


import com.terry.security.demo.springsecurity6demo.user.entity.User;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class UserDetailService implements UserDetailsService {
	public static final Collection<User> users = List.of(
			User.builder().id(1).name("terry").password("123").username("phandinhthe").mailAddress("phandinhthe1991@gmail.com").authorities("ADMIN").build(),
			User.builder().id(2).name("the").password("123").username("terry_phan1991").mailAddress("terry_phan1991@gmail.com").authorities("ADMIN").build(),
			User.builder().id(3).name("phan").password("123").username("trn.frank2802").mailAddress("trn.frank2802@gmail.com").authorities("ADMIN").build(),
			User.builder().id(3).name("trn").password("123").username("trn").mailAddress("trn.frank2802@yahoo.com").authorities("GUEST").build()
	);

	public Collection<User> users() {
		return users;
	}

	public User user(String username, String password, String mailAddress) {
		return users.stream()
				.filter(u -> u.username().equals(username) && u.password().equals(password) && u.mailAddress().equals(mailAddress))
				.findFirst().orElseThrow(RuntimeException::new);
	}

	public User user(String username) {
		return users.stream()
				.filter(u -> u.username().equals(username))
				.findFirst().orElseThrow(RuntimeException::new);
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = user(username);
		return new org.springframework.security.core.userdetails.User(
				user.username(), user.password()
				, Optional.ofNullable(user.authorities()).orElse(Collections.emptyList())
				.stream().map(SimpleGrantedAuthority::new).toList()
		);
	}
}
