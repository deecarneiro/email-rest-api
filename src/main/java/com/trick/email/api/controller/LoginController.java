package com.trick.email.api.controller;

import java.security.NoSuchAlgorithmException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trick.email.api.domain.model.User;
import com.trick.email.api.domain.service.GenerateTokenService;
import com.trick.email.api.domain.service.LoginService;
import com.trick.email.api.domain.view.UserLoginView;

import ch.qos.logback.core.net.ObjectWriter;

@RestController
public class LoginController {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private GenerateTokenService generateTokenService;

	@PostMapping("/users/login")
	public String login(@RequestBody String emailPassword)
			throws NoSuchAlgorithmException, JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		User userView = mapper.readValue(emailPassword, User.class);
		User user = loginService.login(userView.getEmail(), userView.getPassword());
		return generateTokenService.generate(user).getToken();

	}

}
