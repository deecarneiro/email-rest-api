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

import com.trick.email.api.domain.model.User;
import com.trick.email.api.domain.repository.UserRepository;
import com.trick.email.api.domain.view.UserView;

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

	@PostMapping("/users/email")
	public ResponseEntity<User> getByEmail(@RequestBody String emailString){
		Optional<User> user = userRepository.findByemail(emailString);

		if(user.isPresent()) {
			return ResponseEntity.ok(user.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping("/users/search")
	public List<UserView> search(@RequestBody String inputString) {
	    return userRepository.findAllByInputString("%"+inputString+"%");
	}

	@PostMapping("/users")
	@ResponseStatus(HttpStatus.CREATED)
	public User createUser( @Valid @RequestBody User user) {
		return userRepository.save(user);
	}

	@PostMapping("/users/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
		if(!userRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		user.setId(id);
		user = userRepository.save(user);
		return ResponseEntity.ok(user);

	}

	@DeleteMapping("users/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Long id) {
		if(!userRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		userRepository.deleteById(id);
		return ResponseEntity.noContent().build();

	}
}
