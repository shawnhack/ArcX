package com.bitmind.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bitmind.dao.entity.User;
import com.bitmind.form.RegisterForm;
import com.bitmind.form.SelectForm;
import com.bitmind.service.UserService;

@Controller
public class UserController extends AbstractController {

	private static Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	protected AuthenticationManager authenticationManager;

	@RequestMapping(value = "/userList", method = RequestMethod.GET)
	public String userList(Model model) {
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "userList";
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public String deleteAddress(@ModelAttribute("selectForm") SelectForm form,
			Model model) {

		List<Long> userIds = form.getIds();
		// userService.deleteUsers(userIds);

		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);

		return "userList";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model,
			@RequestParam(value = "auth", required = false) String param) {

		if ("error".equals(param)) {
			addKeyError(model, "error.login.invalid");
		}

		return "login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "register";
	}

	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public String registerProcess(Model model,
			@ModelAttribute("registerForm") @Valid RegisterForm form,
			HttpServletRequest request) {

		String email = form.getEmail();
		String username = form.getUsername();
		String password = form.getPassword();
		User user = new User(email, username, password);

		log.info("Registering " + username + " " + email);
		userService.saveUser(user);
		authenticateUser(model, request, username, password);

		return "home";
	}

	/**
	 * @param request
	 * @param username
	 * @param password
	 */
	private void authenticateUser(Model model, HttpServletRequest request,
			String username, String password) {

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				username, password);

		Authentication authenticatedUser = authenticationManager
				.authenticate(token);

		SecurityContext context = SecurityContextHolder.getContext();
		context.setAuthentication(authenticatedUser);

	}

	@ExceptionHandler(AuthenticationException.class)
	public ModelAndView handleException(AuthenticationException e) {
		log.error(e.getMessage());
		ModelAndView model = new ModelAndView("register");
		addError(model, e.getMessage());
		return model;
	}

}
