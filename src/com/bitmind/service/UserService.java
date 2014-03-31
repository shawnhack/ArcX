package com.bitmind.service;

import java.util.List;

import com.bitmind.dao.entity.User;

public interface UserService {

	public User saveUser(User user);

	public User getUserByName(String username);

	public User getUserById(Object id);

	public void deleteUser(String username);

	public void updateUser(User user);

	public List<User> getAllUsers();

	public void deleteUsers(List<Object> userIds);

}