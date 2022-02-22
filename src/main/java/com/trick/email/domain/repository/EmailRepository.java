package com.trick.email.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trick.email.domain.enums.STATUS;
import com.trick.email.domain.model.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
	
	List<Email> findByidUser(long idUser);
	List<Email> findBystatus(STATUS status);

}
