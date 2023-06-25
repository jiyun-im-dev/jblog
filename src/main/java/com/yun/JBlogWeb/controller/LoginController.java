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

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@GetMapping("/auth/login")
	public String login() {
		return "system/login"; // WEB-INF/jblog/system/login.jsp 파일을 뷰로 사용
	}

	// btn-login 버튼을 눌렀을 때 POST 요청이 전송되는 듯?
	@PostMapping("/auth/login")
	public @ResponseBody ResponseDto<?> login(@RequestBody User user, HttpSession session) {
		User findUser = userService.getUser(user.getUsername());

		if (findUser.getUsername() == null) {
			return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), "아이디가 존재하지 않습니다.");
		} else {
			if (user.getPassword().equals(findUser.getPassword())) {
				// 로그인 성공 시 세션에 사용자 정보 저장
				session.setAttribute("principal", findUser);
				return new ResponseDto<>(HttpStatus.OK.value(), findUser.getUsername() + "님 로그인 성공");
			} else {
				return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), "비밀번호 오류");
			}
		}
	}

	// 브라우저와 연결된 세션을 강제 종료할 때 로그아웃 기능 실행
	@GetMapping("/auth/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/"; // 메인 페이지로 리다이렉트
	}

}
