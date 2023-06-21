package com.yun.JBlogWeb.controller;

import com.yun.JBlogWeb.domain.User;
import com.yun.JBlogWeb.dto.ResponseDto;
import com.yun.JBlogWeb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/auth/insertUser")
	public String insertUser() {
		return "user/insertUser";
	}

	// 어떤 타입의 데이터가 반환될지 특정할 수 없으므로 ResponseDto<String>이 아님
	@PostMapping("/auth/insertUser")
	public @ResponseBody ResponseDto<?> insertUser(@RequestBody User user) {
		User findUser = userService.getUser(user.getUsername());

		if (findUser.getUsername() == null) {
			userService.insertUser(user);
			return new ResponseDto<>(HttpStatus.OK.value(), user.getUsername() + "님 가입 성공");
		} else {
			return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), user.getUsername() + "님은 이미 가입되어 있습니다.");
		}

	}

}
