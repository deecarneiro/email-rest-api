package com.trick.email.domain.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trick.email.domain.enums.STATUS;

@Entity
@Table(name="email")
public class Email{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
//	@ManyToMany(fetch= FetchType.LAZY)
//	@JoinColumns({
//        @JoinColumn(name="idEmail", referencedColumnName="id"),
//        @JoinColumn(name="idUser", referencedColumnName="id")
//    })
//	@JsonIgnore
//	private List<User> sender;
//	
//	@ManyToMany
//	@JsonIgnore
//	private List<User> cc;
//	
	private String subject, body, assignment, label;
	
	@Column(name="id_user")
	private int idUser;
	
	private STATUS status;
	
	public Email(long id, String subject, String body, String assignment, String label, STATUS status) {
		this.id = id;
		this.subject = subject;
		this.body = body;
		this.assignment = assignment;
		this.label = label;
		this.status = status;
	}

	public Email(long id,String subject, String body, String assignment,
			String label, int idUser, STATUS status) {
		super();
		this.id = id;
//		this.sender = sender;
//		this.cc = cc;
		this.subject = subject;
		this.body = body;
		this.assignment = assignment;
		this.label = label;
		this.idUser = idUser;
		this.status = status;
	}

	public Email() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

//	public List<User> getSender() {
//		return sender;
//	}
//
//	public void setSender(List<User> sender) {
//		this.sender = sender;
//	}
//
//	public List<User> getCc() {
//		return cc;
//	}
//
//	public void setCc(List<User> cc) {
//		this.cc = cc;
//	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getAssignment() {
		return assignment;
	}

	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public long getUserId() {
		return idUser;
	}

	public void setUserId(int userId) {
		this.idUser = userId;
	}

	public STATUS getStatus() {
		return status;
	}

	public void setStatus(STATUS status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Email other = (Email) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
}
