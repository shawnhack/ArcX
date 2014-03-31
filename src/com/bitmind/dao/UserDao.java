package com.bitmind.dao;

import java.util.List;

import com.bitmind.dao.entity.User;

public interface UserDao {

	public User saveUser(User user);

	public User updateUser(User user);

	public User getUserByName(String username);

	public List<User> getAllUsers();

	public void deleteUsers(List<Object> userIds);

	public User getUserById(Object id);

}