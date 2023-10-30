package com.yun.JBlogWeb.controller;

import com.yun.JBlogWeb.domain.User;
import com.yun.JBlogWeb.service.KakaoLoginService;
import com.yun.JBlogWeb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class KakaoLoginController {

	@Autowired
	private KakaoLoginService kakaoLoginService;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Value("${kakao.default.password}")
	private String kakaoPassword;

	@GetMapping("/oauth/kakao")
	public @ResponseBody String kakaoCallback(String code) {
		// 1. 인증 서버로부터 받은 CODE를 이용해 액세스 토큰 획득
		String accessToken = kakaoLoginService.getAccessToken(code);
		// 2. 액세스 토큰을 이용하여 사용자 정보 획득
		User kakaoUser = kakaoLoginService.getUserInfo(accessToken);
		// 3. 기존 회원이 아니면 신규 회원으로 등록
		User findUser = userService.getUser(kakaoUser.getUsername());
		if (findUser.getUsername() == null) {
			userService.insertUser(kakaoUser);
		}
		// 4. 카카오로부터 받은 사용자 정보를 기반으로 인증 처리
		UsernamePasswordAuthenticationToken authenticationToken = // 인증 토큰 생성?
				new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), kakaoPassword);
		Authentication authentication = authenticationManager.authenticate(authenticationToken); // 토큰을 인증?
		SecurityContextHolder.getContext().setAuthentication(authentication); // 인증 정보로 설정?
		return "redirect:/";
	}

}
