package com.terry.security.demo.springsecurity6demo.user.api;

import com.terry.security.demo.springsecurity6demo.user.entity.User;
import com.terry.security.demo.springsecurity6demo.user.service.JwtTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {
	private final JwtTokenService service;

	public TokenController(JwtTokenService service) {
		this.service = service;
	}

	@PostMapping("/api/login")
	public ResponseEntity<String> authenticate(@RequestBody User user) {
		return ResponseEntity.ok(service.generate(user));
	}
}
