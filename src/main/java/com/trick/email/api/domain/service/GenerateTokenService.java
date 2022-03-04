package com.trick.email.api.domain.service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trick.email.api.domain.model.User;
import com.trick.email.api.domain.model.UserToken;
import com.trick.email.api.domain.repository.UserTokenRepository;
import com.trick.email.api.domain.security.UserUtils;

@Service
public class GenerateTokenService {

	@Autowired
	private UserTokenRepository userTokenRepository;
	
	public UserToken generate(User user) {
		UserToken userTokenSet = new UserToken();
		if(!user.getEmail().isEmpty()) {
			Optional<UserToken> userToken = userTokenRepository.findByiduser(user.getId());
			if(userToken.isPresent()) {
			 
				return userToken.get();
			}else {
				userTokenSet.setIduser(user.getId());
				userTokenSet.setToken(UserUtils.generateToken(user));
				userTokenRepository.save(userTokenSet);
			}
		}
		return userTokenSet;
	}
	
}
