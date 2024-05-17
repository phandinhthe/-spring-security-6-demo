package com.terry.security.demo.springsecurity6demo.user.api;

import com.terry.security.demo.springsecurity6demo.user.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@GetMapping("/user-management/users")
	public Collection<User> users() {
		return List.of(
				User.builder().id(1).name("terry").mailAddress("phandinhthe1991@gmail.com").build(),
				User.builder().id(2).name("the").mailAddress("terry_phan1991@gmail.com").build(),
				User.builder().id(3).name("phan").mailAddress("trn.frank2802@gmail.com").build(),
				User.builder().id(3).name("trn").mailAddress("trn.frank2802@yahoo.com").build()
		);
	}
}
