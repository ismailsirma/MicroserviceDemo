package com.sirmam.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.sirmam.security.dto.WebUser;
import com.sirmam.security.service.provider.JwtTokenProvider;

@RestController
@RequestScope
@RequestMapping("login")
@CrossOrigin
public class LoginController {
	@Autowired
	private AuthenticationManager authMngr;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@PostMapping
	public String signin(@RequestBody @Validated WebUser webuser) {
		try {
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					webuser.getUsername(), webuser.getPassword());
			authMngr.authenticate(authToken);
			return jwtTokenProvider.createToken(webuser.getUsername());
		} catch (Exception e) {
			System.err.println("Log in failed for user " + webuser.getUsername());
		}
		return "";
	}

}