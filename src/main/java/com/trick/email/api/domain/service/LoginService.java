package com.trick.email.api.domain.service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trick.email.api.domain.model.User;
import com.trick.email.api.domain.model.UserToken;
import com.trick.email.api.domain.repository.UserRepository;
import com.trick.email.api.domain.repository.UserTokenRepository;
import com.trick.email.api.domain.security.UserUtils;

@Service
public class LoginService {

	@Autowired
	private UserRepository userRepository;

	public User login(String email, String password) throws NoSuchAlgorithmException {
		String hashPass = UserUtils.md5(password);
		User user = userRepository.findByEmailAndPassword("%"+email+"%", "%"+hashPass+"%");
		return user;
	}

}
