package com.test.service;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bitmind.dao.entity.Address;
import com.bitmind.dao.entity.User;
import com.bitmind.domain.AssetType;
import com.bitmind.service.ServiceException;
import com.bitmind.service.UserService;

public class UserServiceTest extends AbstractServiceTest {

	@Autowired
	private UserService userService;

	@Test
	public void testDeleteUsers() {

		User user = buildUser();
		user.setUsername("456");
		user = userService.saveUser(user);

		user = buildUser();
		user.setUsername("123");
		user = userService.saveUser(user);

		List<Object> userIds = Arrays.asList(new Object[] { user.getId() });

		userService.deleteUsers(userIds);

		List<User> users = userService.getAllUsers();
		for (User u : users) {
			System.out.println(u.getUsername());
			System.out.println(u.getEmail());
			System.out.println(u.getParentKey());
			// System.out.println(u.getPortfolio());
		}
	}

	@Test
	public void testGetAllUsers() {

		User user = buildUser();
		userService.saveUser(user);

		List<User> users = userService.getAllUsers();

		for (User u : users) {
			System.out.println(u.getUsername());
			System.out.println(u.getEmail());
			System.out.println(u.getPortfolio());
		}
	}

	@Test
	public void testGetUserById() {

		User user = buildUser();
		System.out.println(user.getId());

		user = userService.saveUser(user);

		System.out.println(user.getId());

		user = userService.getUserById(user.getId());
		System.out.println(user.getPassword());
	}

	@Test
	public void testGetUser_DifferentCase() {

		User user = buildUser();
		userService.saveUser(user);

		String username = "name";
		user = userService.getUserByName(username);
		System.out.println(user.getPassword());
	}

	@Test
	public void testLoadUserByUsername() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveUser() {

		User user = buildUser();
		userService.saveUser(user);
	}

	@Test(expected = ServiceException.class)
	public void testSaveUserDuplicate() {

		User user = buildUser();

		userService.saveUser(user);
		userService.saveUser(user);
	}

	@Test
	public void testUpdateUser() {
		User user = buildUser();

		userService.saveUser(user);

		user = userService.getUserByName(user.getUsername());

		System.out.println(user.getPassword());

		Address address = new Address();
		address.setAddress("234523gafgsdfg");
		user.getPortfolio().getWallet(AssetType.BTC).addAddress(address);

		userService.updateUser(user);

		User user2 = userService.getUserByName(user.getUsername());

		System.out.println(user2.getId());

		System.out.println(user2.getPortfolio().getParentKey());

		System.out.println(user2.getPortfolio().getWallet(AssetType.BTC)
				.getAddresses().get(0).getAddress());

	}

	private User buildUser() {
		String username = "name";
		String email = "email";
		String password = "pass";
		User user = new User(email, username, password);
		return user;
	}

}
