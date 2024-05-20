package com.terry.security.demo.springsecurity6demo.user.service;


import com.terry.security.demo.springsecurity6demo.user.entity.User;
import com.terry.security.demo.springsecurity6demo.user.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Component
public class UserDetailService implements UserDetailsService {
	private final UserRepository repository;

	public UserDetailService(UserRepository repository) {
		this.repository = repository;
	}

	public Collection<User> users() {
		return repository.findAll();
	}

	public User user(String username, String password, String mailAddress) {
		return users().stream()
				.filter(u -> u.username().equals(username) && u.password().equals(password) && u.mailAddress().equals(mailAddress))
				.findFirst().orElseThrow(RuntimeException::new);
	}

	public User user(String username) {
		return users().stream()
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
