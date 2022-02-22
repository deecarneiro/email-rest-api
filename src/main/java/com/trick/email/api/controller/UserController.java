package com.trick.email.api.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trick.email.domain.model.User;

@RestController
public class UserController {

	@PersistenceContext
	private EntityManager manager;
	
	@GetMapping("/users")
	public List<User> list() {
		return manager.createQuery("from User", User.class).getResultList();
	}
}
