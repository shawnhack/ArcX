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
public class UserService implements UserDetailsService {

	@Resource
	private UserDao userDao;

	public void saveUser(String email, String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);

		User user = new User(email, email, hashedPassword);
		userDao.saveUser(user);
	}

	public void updateUser(User user) {
		userDao.saveUser(user);
	}

	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {

		User user = userDao.getUser(email);

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

}
