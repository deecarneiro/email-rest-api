package com.trick.email.api.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.trick.email.domain.model.User;
import com.trick.email.domain.model.UserPropertiesAndContent;
import com.trick.email.domain.repository.UserRepository;

@RestController
public class UserController {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/users")
	public List<User> list() {
		return userRepository.findAll();
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getById(@PathVariable Long id){
		Optional<User> user = userRepository.findById(id);

		if(user.isPresent()) {
			return ResponseEntity.ok(user.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping("/users/search")
	public List<UserPropertiesAndContent> search(@RequestBody String inputString) {
	    return userRepository.findAllByInputString("%"+inputString+"%");
	}
	
	@PostMapping("/users")
	@ResponseStatus(HttpStatus.CREATED)
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
}
