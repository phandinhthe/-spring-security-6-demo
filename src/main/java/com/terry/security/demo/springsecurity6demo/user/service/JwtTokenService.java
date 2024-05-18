package com.terry.security.demo.springsecurity6demo.user.service;

import com.terry.security.demo.springsecurity6demo.user.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Service
public class JwtTokenService {

	@Value("${terry.authorization.configuration.secret}")
	private String secret;

	public String generate(User user) {
		final long expiration = 3_600_000;
		return Jwts.builder()
				.setSubject(user.username())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret)), SignatureAlgorithm.HS256)
				.compact();
	}

	private Claims claims(String token) {
		return Jwts
				.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret)))
				.build().parseClaimsJws(token).getBody();

	}

	public boolean isValid(String token) {
		try {
			claims(token);
			return true;
		} catch (MalformedJwtException ex) {
			throw new RuntimeException("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			throw new RuntimeException("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			throw new RuntimeException("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			throw new RuntimeException("JWT claims string is empty.");
		}
	}

	public String subject(String token) {
		return claims(token).getSubject();
	}
}
