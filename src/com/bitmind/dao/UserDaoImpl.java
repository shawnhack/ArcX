package com.bitmind.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import org.springframework.stereotype.Repository;

import com.bitmind.dao.entity.User;
import com.googlecode.objectify.Ref;

@Repository
public class UserDaoImpl implements UserDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bitmind.dao.UserDao#saveUser(java.lang.String, java.lang.String)
	 */
	@Override
	public User saveUser(User user) {
		// ofy().save().entities(user.getPortfolio()).now();
		ofy().save().entity(user).now();
		return user;
	}

	@Override
	public User getUser(String email) {

		Ref<User> user = ofy().load().type(User.class).filter("email", email)
				.first();

		return user.getValue();
	}

	@Override
	public User updateUser(User user) {
		ofy().save().entity(user).now();
		return user;
	}
}
