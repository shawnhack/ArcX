package com.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

public class UserControllerTest extends AbstractControllerTest {

	@Test
	public void testLogin() throws Exception {
		mockMvc.perform(get("/login")).andExpect(status().isOk())
				.andExpect(view().name("login"));
	}

	@Test
	public void testRegister() throws Exception {
		mockMvc.perform(get("/register")).andExpect(status().isOk())
				.andExpect(view().name("register"));
	}

	@Test
	public void testRegisterProcess() throws Exception {
		mockMvc.perform(
				post("/registerProcess").param("email", "mail@test.com")
						.param("username", "username")
						.param("password", "password"))
				.andExpect(status().isOk()).andExpect(view().name("home"));
	}

	@Test
	public void testLogin_Error() throws Exception {
		mockMvc.perform(get("/login").param("auth", "error"))
				.andExpect(status().isOk()).andExpect(view().name("login"))
				.andExpect(model().attributeExists("error_msg"));

	}
}
