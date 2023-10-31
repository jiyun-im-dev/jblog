package com.yun.JBlogWeb.controller;

import com.yun.JBlogWeb.domain.OAuthType;
import com.yun.JBlogWeb.domain.User;
import com.yun.JBlogWeb.dto.ResponseDto;
import com.yun.JBlogWeb.dto.UserDto;
import com.yun.JBlogWeb.security.UserDetailsImpl;
import com.yun.JBlogWeb.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@Value("${kakao.default.password}")
	private String kakaoPassword;

	@GetMapping("/auth/login")
	public String login() {
		return "system/login";
	}

	@GetMapping("/auth/insertUser")
	public String insertUser() {
		return "user/insertUser";
	}

	// 어떤 타입의 데이터가 반환될지 특정할 수 없으므로 ResponseDto<String>이 아님
	@PostMapping("/auth/insertUser")
	public @ResponseBody ResponseDto<?> insertUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
		// UserDto -> User 객체로 변환
		User user = modelMapper.map(userDto, User.class);
		User findUser = userService.getUser(user.getUsername());

		if (findUser.getUsername() == null) {
			userService.insertUser(user);
			return new ResponseDto<>(HttpStatus.OK.value(), user.getUsername() + "님 가입 성공");
		} else {
			return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), user.getUsername() + "님은 이미 가입되어 있습니다.");
		}
	}

	@GetMapping("/user/updateUser")
	public String updateUser() {
		return "user/updateUser";
	}

	@PutMapping("/user")
	public @ResponseBody ResponseDto<?> updateUser(@RequestBody User user, @AuthenticationPrincipal UserDetailsImpl principal) {
		// 회원 정보 수정 전, 로그인한 사용자가 카카오 회원인지 확인
		if (principal.getUser().getOAuthType() == OAuthType.KAKAO) {
			// 카카오 회원인 경우 비밀번호 고정
			user.setPassword(kakaoPassword);
		}
		// 회원 정보 수정과 동시에 세션 갱신
		principal.setUser(userService.updateUser(user));
		return new ResponseDto<>(HttpStatus.OK.value(), user.getUsername() + " 수정 완료");
	}

}
