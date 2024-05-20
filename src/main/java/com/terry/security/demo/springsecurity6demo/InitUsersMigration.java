package com.terry.security.demo.springsecurity6demo;

import com.terry.security.demo.springsecurity6demo.user.entity.User;
import com.terry.security.demo.springsecurity6demo.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class InitUsersMigration {
	@Autowired
	private UserRepository repository;

	@EventListener(ApplicationReadyEvent.class)
	public void init() {
		final Collection<User> users = List.of(
				User.builder().id(1).name("terry").password("123").username("phandinhthe").mailAddress("phandinhthe1991@gmail.com").authorities("ADMIN").build(),
				User.builder().id(2).name("the").password("123").username("terry_phan1991").mailAddress("terry_phan1991@gmail.com").authorities("ADMIN").build(),
				User.builder().id(3).name("phan").password("123").username("trn.frank2802").mailAddress("trn.frank2802@gmail.com").authorities("ADMIN").build(),
				User.builder().id(4).name("trn").password("123").username("trn").mailAddress("trn.frank2802@yahoo.com").authorities("GUEST").build()
		);
		repository.saveAll(users);
	}
}
