package com.sirmam.security.service.provider;

import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenProvider {

	private String secretKey;
	private long validityInMilliseconds;

	@Autowired
	public JwtTokenProvider(@Value("${security.jwt.token.secret-key}") String secretKey,
			@Value("${security.jwt.token.expiration}") long milliseconds) {
		this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
		this.validityInMilliseconds = milliseconds;
	}

	public String createToken(String username) {
		Claims claims = Jwts.claims().setSubject(username);

		// Build the Token
		Date now = new Date();
		return Jwts.builder().setClaims(claims).setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + validityInMilliseconds))
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();
	}
}
