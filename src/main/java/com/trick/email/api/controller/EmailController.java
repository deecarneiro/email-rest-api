package com.trick.email.api.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.trick.email.domain.model.Email;
import com.trick.email.domain.model.EmailPropertiesAndContent;
import com.trick.email.domain.repository.EmailRepository;

@RestController
public class EmailController {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private EmailRepository emailRepository;
	
	@GetMapping("/emails")
	public List<Email> list() {
		return emailRepository.findAll();
	}
	
	@GetMapping("/emails/{id}")
	public ResponseEntity<Email> getById(@PathVariable Long id){
		Optional<Email> email = emailRepository.findById(id);
		
		if(email.isPresent()) {
			return ResponseEntity.ok(email.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/emails/search")
	public List<EmailPropertiesAndContent> search(@RequestBody String inputString) {
	    return emailRepository.findAllByInputString("%"+inputString+"%");
	}
}
