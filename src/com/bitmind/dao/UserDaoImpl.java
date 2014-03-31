package com.bitmind.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.bitmind.dao.entity.Parent;
import com.bitmind.dao.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager em;

	private static Parent parent = null;

	private Parent getParent() {
		if (parent == null) {
			// Parent parent = em.find(Parent.class, parentKey);
			parent = new Parent();
			em.persist(parent);
			em.flush();
		}

		return parent;
	}

	@Override
	public void deleteUsers(List<Object> userIds) {

		for (Object id : userIds) {
			User user = getUserById(id);
			em.remove(user);
		}
	}

	@Override
	public User saveUser(User user) {
		user.setParent(getParent());

		em.persist(user);

		return user;
	}

	@Override
	public User updateUser(User user) {
		em.merge(user);
		return user;
	}

	@Override
	public User getUserByName(String username) {

		User user = null;

		// CriteriaBuilder builder = em.getCriteriaBuilder();
		// CriteriaQuery<User> query = builder.createQuery(User.class);
		// EntityType<User> type = em.getMetamodel().entity(User.class);

		Query q = em
				.createQuery("select from User u where u.lowercaseUsername = :username");
		q.setParameter("username", username.toLowerCase());

		// Root<User> user = query.from(User.class);

		// Predicate condition = builder.equal(user.get("username"), username);
		// query.where(condition);

		// TypedQuery<User> q = em.createQuery(query);

		if (q.getResultList().size() == 1) {
			user = (User) q.getSingleResult();
			user.getPortfolio().getWallets();
		}

		return user;
	}

	@Override
	public User getUserById(Object id) {
		// Key key = KeyFactory.createKey(User.class.getSimpleName(), id);
		// System.out.println(key);
		User user = em.find(User.class, id);
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		Query q = em
				.createQuery("select u from User u where u.parentKey = :parentKey");
		q.setParameter("parentKey", getParent().getId());

		@SuppressWarnings("unchecked")
		List<User> users = q.getResultList();
		return users;
	}

}
