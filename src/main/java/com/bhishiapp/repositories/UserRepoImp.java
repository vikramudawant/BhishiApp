package com.bhishiapp.repositories;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bhishiapp.entities.User;

@Repository
public class UserRepoImp implements UserRepo {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public void createUser(User user) {
		Session session = entityManager.unwrap(Session.class);
		session.save(user);

	}

}
