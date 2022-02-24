package com.trick.email.api.config;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)
public class APIError {

    private Integer status;
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime dateTime;
    private String title;
    private List<Field> fields;

    public static class Field{
    	private String name;
    	private String message;

		public Field(String name, String message) {
			super();
			this.name = name;
			this.message = message;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}


    }

	public APIError(Integer status, LocalDateTime dateTime, String title) {
		super();
		this.status = status;
		this.dateTime = dateTime;
		this.title = title;
	}

	public APIError(Integer status, LocalDateTime dateTime, String title, List<Field> fields) {
		super();
		this.status = status;
		this.dateTime = dateTime;
		this.title = title;
		this.fields = fields;

	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Field> getFields() {
		return fields;
	}
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

}