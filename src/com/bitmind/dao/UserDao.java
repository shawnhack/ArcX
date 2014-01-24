package com.bitmind.dao;

import com.bitmind.dao.entity.User;

public interface UserDao {

	public abstract User saveUser(User user);

	public abstract User updateUser(User user);

	public abstract User getUser(String email);

}