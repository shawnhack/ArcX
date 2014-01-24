package com.bitmind.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bitmind.service.ServiceException;

@ControllerAdvice
public class SpringControllerAdvice {

	@ExceptionHandler(ServiceException.class)
	public String handleServiceException(ServiceException ex) {

		System.out.println(ex.getMessage());

		return "errorView";
	}

}
