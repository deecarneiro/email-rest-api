package com.trick.email.api.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trick.email.api.domain.enums.STATUS;
import com.trick.email.api.domain.exception.BusinessException;
import com.trick.email.api.domain.model.Email;
import com.trick.email.api.domain.repository.EmailRepository;
import com.trick.email.api.domain.view.EmailView;

@Service
public class EmailCrudService {

	@Autowired
	private EmailRepository emailRepository;

	public Email save(Email email) {
		return emailRepository.save(email);
	}

	public List<Email> list(){
		return emailRepository.findAll();
	}

	public Optional<Email> getById(long id) {
		return Optional.ofNullable(emailRepository.getById(id));
	}

	public List<EmailView> search(String term){
		return emailRepository.findAllByInputString(term);
	}

	public Email update(Long id, Email email) {
		if(email.getStatus() != STATUS.DRAFT) {
			throw new BusinessException("This email can't be updated. Just drafts can be updated!");
		}
		email.setId(id);
		return emailRepository.save(email);
	}

	public boolean exists(Long id) {
		return emailRepository.existsById(id);
	}


	public void delete(Long id) {
		if(!emailRepository.existsById(id)) {
			throw new BusinessException("This email can't be deleted. It doesn't exist!");
		}
		emailRepository.deleteById(id);
	}
}
