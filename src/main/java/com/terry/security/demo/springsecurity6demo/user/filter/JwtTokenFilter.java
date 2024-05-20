package com.terry.security.demo.springsecurity6demo.user.filter;

import com.terry.security.demo.springsecurity6demo.user.service.JwtTokenService;
import com.terry.security.demo.springsecurity6demo.user.service.UserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

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
		String username = jwtTokenService.subject(jwtToken);
		UserDetails user = userDetailService.loadUserByUsername(username);
		if (null == user) {
			throw new RuntimeException(String.format("User %s doesn't exist.", username));
		}

		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
				username, user.getPassword(), user.getAuthorities()
		));
		filterChain.doFilter(request, response);
	}
}
