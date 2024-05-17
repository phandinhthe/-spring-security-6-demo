package com.terry.security.demo.springsecurity6demo.user.service;


import com.terry.security.demo.springsecurity6demo.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserDetailService {
	private static final Collection<User> users = List.of(
			User.builder().id(1).name("terry").mailAddress("phandinhthe1991@gmail.com").build(),
			User.builder().id(2).name("the").mailAddress("terry_phan1991@gmail.com").build(),
			User.builder().id(3).name("phan").mailAddress("trn.frank2802@gmail.com").build(),
			User.builder().id(3).name("trn").mailAddress("trn.frank2802@yahoo.com").build()
	);

	public Collection<User> users() {
		return users;
	}

	public User user(String name) {
		return users.stream().filter(u -> u.name().equals(name)).findFirst().orElseThrow(RuntimeException::new);
	}
}
