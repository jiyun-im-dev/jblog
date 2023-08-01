package com.yun.JBlogWeb.controller;

import com.yun.JBlogWeb.domain.User;
import com.yun.JBlogWeb.dto.ResponseDto;
import com.yun.JBlogWeb.dto.UserDto;
import com.yun.JBlogWeb.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/auth/insertUser")
	public String insertUser() {
		return "user/insertUser";
	}

	// 어떤 타입의 데이터가 반환될지 특정할 수 없으므로 ResponseDto<String>이 아님
	@PostMapping("/auth/insertUser")
	public @ResponseBody ResponseDto<?> insertUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
		// UserDto 객체에 대한 유효성 검사
		if (bindingResult.hasErrors()) {
			// 에러가 하나라도 있다면 에러 메시지를 Map에 등록
			Map<String, String> errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), errorMap);
		}

		User user = modelMapper.map(userDto, User.class);
		User findUser = userService.getUser(user.getUsername());

		if (findUser.getUsername() == null) {
			userService.insertUser(user);
			return new ResponseDto<>(HttpStatus.OK.value(), user.getUsername() + "님 가입 성공");
		} else {
			return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), user.getUsername() + "님은 이미 가입되어 있습니다.");
		}

	}

}
