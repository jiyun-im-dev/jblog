package com.yun.JBlogWeb.service;

import com.yun.JBlogWeb.domain.Role;
import com.yun.JBlogWeb.domain.User;
import com.yun.JBlogWeb.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public User updateUser(User user) {
		User findUser = userRepository.findById(user.getId()).get();
		findUser.setUsername(user.getUsername());
		findUser.setPassword(user.getPassword());
		findUser.setEmail(user.getEmail());
		return findUser;
	}

	@Transactional(readOnly = true)
	public User getUser(String username) {
		User findUser = userRepository.findByUsername(username).orElseGet(() -> {
			return new User();
		});
		return findUser;
	}

	@Transactional
	public void insertUser(User user) {
		// 비밀번호를 암호화하여 설정한다.
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		user.setRole(Role.USER);
		userRepository.save(user);
	}

}
