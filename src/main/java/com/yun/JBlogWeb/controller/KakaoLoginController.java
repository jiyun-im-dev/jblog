package com.yun.JBlogWeb.controller;

import com.yun.JBlogWeb.service.KakaoLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class KakaoLoginController {

	@Autowired
	private KakaoLoginService kakaoLoginService;

	@GetMapping("/oauth/kakao")
	public @ResponseBody String kakaoCallback(String code) {
		// 1. 인증 서버로부터 받은 CODE를 이용해 액세스 토큰 획득
		String accessToken = kakaoLoginService.getAccessToken(code);
		// 2. 액세스 토큰을 이용하여 사용자 정보 획득
		String userInfo = kakaoLoginService.getUserInfo(accessToken);
		return userInfo;
	}

}
