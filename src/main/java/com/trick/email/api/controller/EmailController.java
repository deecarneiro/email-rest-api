package com.trick.email.api.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.trick.email.api.domain.model.Email;
import com.trick.email.api.domain.model.EmailPropertiesAndContent;
import com.trick.email.api.domain.repository.EmailRepository;
import com.trick.email.api.domain.service.EmailCrudService;

@RestController
public class EmailController {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private EmailCrudService emailCrudService;

	@GetMapping("/emails")
	public List<Email> list() {
		return emailCrudService.list();
	}

	@GetMapping("/emails/{id}")
	public ResponseEntity<Email> getById(@PathVariable Long id){
		Optional<Email> email = emailCrudService.getById(id);

		if(email.isPresent()) {
			return ResponseEntity.ok(email.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping("/emails/search")
	public List<EmailPropertiesAndContent> search(@RequestBody String inputString) {
	    return emailCrudService.search("%"+inputString+"%");
	}

	@PostMapping("/emails")
	@ResponseStatus(HttpStatus.CREATED)
	public Email createEmail(@Valid @RequestBody Email email) {
		return emailCrudService.save(email);
	}

	@PostMapping("/emails/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void updateEmail(@PathVariable Long id, @Valid @RequestBody Email email) {

		email.setId(id);
		email = emailCrudService.save(email);

	}
	
	@DeleteMapping("emails/{id}")
	public void deleteEmail(@PathVariable Long id) {
		emailCrudService.delete(id);
	}
}
