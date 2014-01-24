package com.bitmind.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.bitmind.dao.entity.User;

public class AbstractController {
	@Autowired
	private MessageSource messageSource;

	protected String getMessage(String key) {
		return messageSource.getMessage(key, null, Locale.US);
	}

	protected void addKeyError(Model model, String key) {
		model.addAttribute("error_msg", getMessage("error.login.invalid"));
	}

	protected void addKeyMessage(Model model, String key) {
		model.addAttribute("success_msg", getMessage("message.address.deleted"));
	}

	protected void addError(Model model, String message) {
		model.addAttribute("error_msg", message);
	}

	protected void addError(ModelAndView model, String message) {
		model.addObject("error_msg", message);
	}

	protected User getUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
	}
}
