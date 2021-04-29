package com.spring.movlib.controller;

import javax.xml.bind.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import com.spring.movlib.util.ErrorMessage;


@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ValidationException.class)
	ErrorMessage badRequestExceptionHandler (ValidationException e){
		return new  ErrorMessage("400", e.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(ResponseStatusException.class)
	ResponseEntity<String> ResposneStatusExceptionHandler (ResponseStatusException e){
		return new ResponseEntity<String>(e.getReason(), e.getStatus());

	}
}
