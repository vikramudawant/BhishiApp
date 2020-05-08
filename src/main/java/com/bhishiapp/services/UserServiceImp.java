package com.bhishiapp.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bhishiapp.entities.Member;
import com.bhishiapp.entities.User;
import com.bhishiapp.repositories.UserRepo;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepo repo;
	
	@Transactional
	@Override
	public void createUser(Member member) {
		User user = new User();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setUsername(member.getName());
		user.setPassword(encoder.encode(member.getMobile()));
		user.setRole("USER");
		repo.createUser(user);

	}

}
