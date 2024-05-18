package com.terry.security.demo.springsecurity6demo.user.api;

import com.terry.security.demo.springsecurity6demo.user.entity.User;
import com.terry.security.demo.springsecurity6demo.user.service.UserDetailService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/user-management/users")
	public Collection<User> users() {
		return UserDetailService.users;
	}
}
