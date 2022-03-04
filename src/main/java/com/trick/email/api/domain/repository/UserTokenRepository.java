package com.trick.email.api.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trick.email.api.domain.model.User;
import com.trick.email.api.domain.model.UserToken;

public interface UserTokenRepository extends JpaRepository<UserToken, Long>{

	Optional<UserToken> findByiduser(long id);

}
