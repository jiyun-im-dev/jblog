package com.yun.JBlogWeb.controller;

import com.yun.JBlogWeb.domain.Role;
import com.yun.JBlogWeb.domain.User;
import com.yun.JBlogWeb.exception.JBlogException;
import com.yun.JBlogWeb.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/user")
	public @ResponseBody String insertUser(@RequestBody User user) {
		user.setRole(Role.USER);
		userRepository.save(user);
		return user.getUsername() + " 회원 가입 성공";
	}

	@GetMapping("/user/get/{id}")
	public @ResponseBody User getUser(@PathVariable int id) {
		User findUser = userRepository.findById(id).orElseThrow(() -> {
			return new JBlogException(id + "번 회원이 없습니다.");
		});
		return findUser;
	}

	// 해당 id를 가진 회원 정보 수정
	@PutMapping("/user")
	public @ResponseBody String updateUser(@RequestBody User user) {
		User findUser = userRepository.findById(user.getId()).orElseThrow(() -> {
			return new JBlogException(user.getId() + "번 회원이 없습니다.");
		});
		findUser.setUsername(user.getUsername());
		findUser.setPassword(user.getPassword());
		findUser.setEmail(user.getEmail());
		userRepository.save(user); // save() 메서드는 저장과 수정을 지원
		return user.getId() + "번 회원 수정 성공";
	}

	@DeleteMapping("/user/{id}")
	public @ResponseBody String DeleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
		return id + "번 회원 삭제 성공";
	}

	@GetMapping("/user/list")
	public @ResponseBody List<User> getUserList() {
		return userRepository.findAll();
	}

	@GetMapping("/user/page/{page}")
	public @ResponseBody Page<User> getUserListPaging(@PathVariable int page) {
		// page에 해당하는 2개의 데이터 조회
		// id와 username 내림차순 정렬
		Pageable pageable = PageRequest.of(page, 2, Sort.Direction.DESC, "id", "username");
		return userRepository.findAll(pageable);
	}

	@GetMapping("/user/page")
	public @ResponseBody Page<User> getUserListPaging2(
			@PageableDefault(
					page = 0,
					size = 2,
					direction = Sort.Direction.DESC,
					sort = {"id", "username"})
			Pageable pageable) {
		// 위 메서드와 동일한 기능
		return userRepository.findAll(pageable);
	}

}
