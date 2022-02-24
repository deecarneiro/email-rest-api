package com.trick.email.api.config;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.trick.email.api.domain.exception.BusinessException;

@ControllerAdvice(basePackages = "com.trick.email.api.controller")
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
	private MessageSource messageSRC;


    @ExceptionHandler(BusinessException.class)
    	public ResponseEntity<Object> businessExceptionHandler(BusinessException ex, WebRequest request) {
		var status = HttpStatus.BAD_REQUEST;
		var apiError = new APIError(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), "Data Integrity Violation");

	return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		var fields = new ArrayList<APIError.Field>();

		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String message = messageSRC.getMessage(error, LocaleContextHolder.getLocale());
			String name = ((FieldError) error).getField();
			fields.add(new APIError.Field(name, message));
		}

		var apiError = new APIError(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), "Data Validation Error", fields);
			return super.handleExceptionInternal(ex, apiError, headers, status, request);
	}

	// Custom Exception Handlers
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<APIError> dataIntegriyException(org.hibernate.exception.ConstraintViolationException ex) {
		var fields = new ArrayList<APIError.Field>();

			String message = ex.getCause().getMessage();
			String name = ex.getConstraintName();
			fields.add(new APIError.Field(name, message));

		return new ResponseEntity<>(
				new APIError(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), "Data Integrity Violation", fields),
				HttpStatus.BAD_REQUEST);
	}
}
