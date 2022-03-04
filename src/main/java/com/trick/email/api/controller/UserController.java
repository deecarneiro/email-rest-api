package com.trick.email.api.controller;

import java.security.NoSuchAlgorithmException;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trick.email.api.domain.model.Email;
import com.trick.email.api.domain.model.User;
import com.trick.email.api.domain.repository.UserRepository;
import com.trick.email.api.domain.service.UserCrudService;
import com.trick.email.api.domain.view.UserView;

@RestController
public class UserController {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private UserCrudService userCrudService;

	@GetMapping("/users")
	public List<User> list() {
		return userCrudService.list();
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getById(@PathVariable Long id){
		Optional<User> user = userCrudService.getById(id);

		if(user.isPresent()) {
			return ResponseEntity.ok(user.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping("/users/email")
	public ResponseEntity<User> getByEmail(@RequestBody String emailString){
		Optional<User> user = userCrudService.getByEmail(emailString);

		if(user.isPresent()) {
			return ResponseEntity.ok(user.get());
		}

		return ResponseEntity.notFound().build();
	}


	@PostMapping("/users")
	@ResponseStatus(HttpStatus.CREATED)
	public User createUser( @Valid @RequestBody User user) throws NoSuchAlgorithmException, JsonMappingException, JsonProcessingException {
		return userCrudService.save(user);
	}

	@PostMapping("/users/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void updateUser(@PathVariable Long id, @Valid @RequestBody User user) throws NoSuchAlgorithmException {
		user.setId(id);
		userCrudService.update(id, user);
	}

	@DeleteMapping("users/{id}")
	public void deleteUser(@PathVariable Long id) {
		userCrudService.delete(id);
	}
}
