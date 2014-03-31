package com.bitmind.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bitmind.dao.UserDao;
import com.bitmind.dao.entity.User;
import com.bitmind.security.AppRole;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

	@Resource
	private UserDao userDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bitmind.service.UserService#saveUser(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public User saveUser(User user) {

		User existingUser = getUserByName(user.getUsername());

		if (existingUser != null) {
			throw new ServiceException("Username already exists");
		}

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPassword);

		userDao.saveUser(user);

		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bitmind.service.UserService#updateUser(com.bitmind.dao.entity.User)
	 */
	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public void deleteUsers(List<Object> userIds) {
		userDao.deleteUsers(userIds);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bitmind.service.UserService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		User user = userDao.getUserByName(username);

		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		List<GrantedAuthority> authorities = buildAuthorities(user.getRole());
		user.setAuthorities(authorities);

		return user;

	}

	/**
	 * @return
	 */
	private List<GrantedAuthority> buildAuthorities(int index) {

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		switch (index) {
		case 1:
			authorities.add(AppRole.ROLE_ADMIN);
		default:
			authorities.add(AppRole.ROLE_USER);
			break;
		}
		return authorities;
	}

	@Override
	public User getUserByName(String username) {
		return userDao.getUserByName(username);
	}

	@Override
	public User getUserById(Object id) {
		return userDao.getUserById(id);
	}

	@Override
	public void deleteUser(String username) {
		// TODO Auto-generated method stub

	}

}
