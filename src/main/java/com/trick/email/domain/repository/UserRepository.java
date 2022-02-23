package com.trick.email.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trick.email.domain.model.User;
import com.trick.email.domain.model.UserPropertiesAndContent;

public interface UserRepository extends JpaRepository<User, Long>{

	List<User> findByname(String name);
	List<User> findBynameContaining(String name);
	List<User> findBynickname(String nickname);
	List<User> findBynicknameContaining(String name);
	List<User> findByemail(String email);
	List<User> findByemailContaining(String name);

	@Query(value = "SELECT u FROM User as u WHERE (:inputString is null or u.name like :inputString ) or " +
            "(:inputString is null or u.nickname like :inputString) or (:inputString is null or u.email like :inputString)"
    )
    List<UserPropertiesAndContent> findAllByInputString( String inputString);


}
