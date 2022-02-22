package com.trick.email.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trick.email.domain.model.Email;

@RestController
public class EmailController {

	@PersistenceContext
	private EntityManager manager;
	
	private List<Email> emails = new ArrayList<>();

	@GetMapping("/emails")
	public List<Email> list() {
		return manager.createQuery("from Email", Email.class).getResultList();
	}
}
