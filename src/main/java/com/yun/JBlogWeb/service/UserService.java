package com.yun.JBlogWeb.service;

import com.yun.JBlogWeb.domain.Role;
import com.yun.JBlogWeb.domain.User;
import com.yun.JBlogWeb.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly = true)
	public User getUser(String username) {
		User findUser = userRepository.findByUsername(username).orElseGet(() -> {
			return new User();
		});
		return findUser;
	}

	@Transactional
	public void insertUser(User user) {
		user.setRole(Role.USER);
		userRepository.save(user);
	}

}
