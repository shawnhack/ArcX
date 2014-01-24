package com.bitmind.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitmind.form.RegisterForm;
import com.bitmind.service.UserService;

@Controller
public class UserController extends AbstractController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model,
			@RequestParam(value = "auth", required = false) String param) {

		if ("error".equals(param)) {
			addKeyError(model, "error.login.invalid");
			// result.reject("error.login.invalid");
		}

		return "login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(@ModelAttribute("registerForm") RegisterForm form,
			BindingResult result) {

		return "register";
	}

	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public String registerProcess(
			@ModelAttribute("registerForm") @Valid RegisterForm form,
			BindingResult result) {

		if (result.hasErrors()) {
			System.out.println("Errors:" + result.getErrorCount());
		}

		if (result.hasFieldErrors()) {
			System.out.println("Field Errors:"
					+ result.getFieldError().getDefaultMessage());
		}

		userService.saveUser(form.getEmail(), form.getPassword());

		return "home";
	}

}
