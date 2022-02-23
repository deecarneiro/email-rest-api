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

import com.trick.email.domain.model.Email;
import com.trick.email.domain.model.EmailPropertiesAndContent;
import com.trick.email.domain.model.User;
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

	@PostMapping("/emails")
	@ResponseStatus(HttpStatus.CREATED)
	public Email createEmail(@Valid @RequestBody Email email) {
		return emailRepository.save(email);
	}

	@PostMapping("/emails/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Email> updateEmail(@PathVariable Long id, @Valid @RequestBody Email email) {

		if(!emailRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		email.setId(id);
		email = emailRepository.save(email);
		return ResponseEntity.ok(email);

	}
	
	@DeleteMapping("emails/{id}")
	public ResponseEntity<Email> deleteUser(@PathVariable Long id) {
		if(!emailRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		emailRepository.deleteById(id);
		return ResponseEntity.noContent().build();

	}
}
