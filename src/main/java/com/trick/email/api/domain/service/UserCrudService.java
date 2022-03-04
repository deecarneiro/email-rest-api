package com.trick.email.api.domain.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trick.email.api.domain.exception.BusinessException;
import com.trick.email.api.domain.model.User;
import com.trick.email.api.domain.repository.UserRepository;
import com.trick.email.api.domain.security.UserUtils;

@Service
public class UserCrudService {

	@Autowired
	private UserRepository userRepository;
	
	public User save(User user) throws NoSuchAlgorithmException {
    	String hashPass = UserUtils.md5(user.getPassword());
	    user.setPassword(hashPass);
		return userRepository.save(user);
	}

	public List<User> list(){
		return userRepository.findAll();
	}

	public Optional<User> getById(long id) {
		return Optional.ofNullable(userRepository.getById(id));
	}
	
	public Optional<User> getByEmail(String email) {
		return Optional.ofNullable(userRepository.findByemail(email));
	}

	public User update(Long id, User user) {
		if(userRepository.existsById(id)) {
			throw new BusinessException("This user can't be updated. Just drafts can be updated!");
		}
		user.setId(id);
		return userRepository.save(user);
	}

	public boolean exists(Long id) {
		return userRepository.existsById(id);
	}


	public void delete(Long id) {
		if(!userRepository.existsById(id)) {
			throw new BusinessException("This user can't be deleted. It doesn't exist!");
		}
		userRepository.deleteById(id);
	}
}
