package com.trick.email.api.domain.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.trick.email.api.domain.enums.STATUS;

@Entity
@Table(name="email")
public class Email{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;

	@Size(max=45)
	private String subject;

	@Size(max=1000)
	private String body;

	@Size(max=255)
	private String assignment;

	@NotNull
	@Size(max=255)
	private String label;

	@Column(name="iduser")
	private long iduser;

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
			String label, long iduser, STATUS status) {
		super();
		this.id = id;
//		this.sender = sender;
//		this.cc = cc;
		this.subject = subject;
		this.body = body;
		this.assignment = assignment;
		this.label = label;
		this.iduser = iduser;
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
		return iduser;
	}

	public void setUserId(long iduser) {
		this.iduser = iduser;
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
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Email other = (Email) obj;
		if (id != other.id)
			return false;
		return true;
	}


}
