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
import com.trick.email.api.domain.service.LoginService;
import com.trick.email.api.domain.view.UserLoginView;

@RestController
public class LoginController {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private LoginService loginService;

	@PostMapping("/users/login")
	public User login(@RequestBody String emailPassword) throws NoSuchAlgorithmException, JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		User userView = mapper.readValue(emailPassword, User.class);
		return loginService.login(userView.getEmail(), userView.getPassword());
	}



}
