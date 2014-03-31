package com.bitmind.controller;

import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.bitmind.dao.entity.User;
import com.bitmind.service.UserService;

public class AbstractController {

	private static Logger log = LoggerFactory
			.getLogger(AbstractController.class);

	@Autowired
	private UserService userService;

	@Autowired
	protected AuthenticationManager authenticationManager;

	@Autowired
	private MessageSource messageSource;

	protected String getMessage(String key) {
		return messageSource.getMessage(key, null, Locale.US);
	}

	protected void addKeyError(Model model, String key) {
		model.addAttribute("error_msg", getMessage(key));
	}

	protected void addKeyMessage(Model model, String key) {
		model.addAttribute("success_msg", getMessage(key));
	}

	protected void addError(Model model, String message) {
		model.addAttribute("error_msg", message);
	}

	protected void addError(ModelAndView model, String message) {
		model.addObject("error_msg", message);
	}

	protected User getUser() {
		SecurityContext context = SecurityContextHolder.getContext();
		User user = (User) context.getAuthentication().getPrincipal();
		return user;
	}

	/**
	 * Session objects need to be put back into session after any update.
	 * 
	 * @param user
	 */
	protected void updateUser(User user, HttpServletRequest request) {
		userService.updateUser(user);
		SecurityContext context = SecurityContextHolder.getContext();

		String test = (new Date()).getTime() + "";
		request.getSession().setAttribute("test", test);

		// UsernamePasswordAuthenticationToken token = new
		// UsernamePasswordAuthenticationToken(
		// user, null);
		// context.getAuthentication().

		// Authentication authenticatedUser = authenticationManager
		// .authenticate(token);

		// context.setAuthentication(token);
	}

}
