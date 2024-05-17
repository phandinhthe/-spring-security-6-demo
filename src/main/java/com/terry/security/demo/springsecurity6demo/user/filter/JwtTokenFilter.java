package com.terry.security.demo.springsecurity6demo.user.filter;

import com.terry.security.demo.springsecurity6demo.user.entity.User;
import com.terry.security.demo.springsecurity6demo.user.service.JwtTokenService;
import com.terry.security.demo.springsecurity6demo.user.service.UserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
	@Autowired
	private JwtTokenService jwtTokenService;
	@Autowired
	private UserDetailService userDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authenticationHeader = request.getHeader("Authorization");
		if (authenticationHeader == null || !authenticationHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		String jwtToken = authenticationHeader.substring(7);
		if (!jwtTokenService.isValid(jwtToken)) {
			throw new RuntimeException("Token is invalid");
		}
		String name = jwtTokenService.subject(jwtToken);
		User user = userDetailService.user(name);
		if (null == user) {
			throw new RuntimeException(String.format("User %s doesn't exist.", name));
		}

		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
				name, null, Collections.emptyList()
		));
		filterChain.doFilter(request, response);
	}
}
