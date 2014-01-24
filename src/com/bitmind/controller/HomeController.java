package com.bitmind.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController extends AbstractController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		System.out.println("Index");
		return "home";
	}

	@RequestMapping("/error")
	public String error(HttpServletRequest request, Model model) {

		model.addAttribute("errorCode",
				request.getAttribute("javax.servlet.error.status_code"));

		Throwable throwable = (Throwable) request
				.getAttribute("javax.servlet.error.exception");

		String errorMessage = "";

		if (throwable != null) {
			errorMessage = throwable.getMessage();
		}

		model.addAttribute("errorMessage", errorMessage.toString());

		return "error";
	}

}
