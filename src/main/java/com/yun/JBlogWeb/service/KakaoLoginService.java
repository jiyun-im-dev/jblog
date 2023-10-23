package com.yun.JBlogWeb.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class KakaoLoginService {

	// 카카오 인증 서버로부터 실시간으로 CODE를 받아온다
	public String getAccessToken(String code) {
		// HttpHeaders 생성 (인터넷 프로토콜인 MIME 종류)
		HttpHeaders header = new HttpHeaders();
		header.add("Content-type",
				"application/x-www-form-urlencoded;charset=utf-8");
		// HttpBody 생성(4개의 필수 매개변수 설정)
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("grant_type", "authorization_code");
		body.add("client_id", "fa741c1308963740644104948305d803");
		body.add("redirect_uri", "http://localhost:8080/oauth/kakao");
		body.add("code", code);
		// HttpHeaders와 HttpBody가 설정된 HttpEntity 객체 생성
		HttpEntity<MultiValueMap<String, String>> requestEntity =
				new HttpEntity<>(body, header);
		// RestTemplate을 이용하면 브라우저 없이 HTTP 요청 처리 가능
		RestTemplate restTemplate = new RestTemplate();
		// HTTP 요청 및 응답 받기
		ResponseEntity<String> responseEntity = restTemplate.exchange(
				"https://kauth.kakao.com/oauth/token", // 액세스 토큰 요청 주소
				HttpMethod.POST, // 요청 방식
				requestEntity,   // 요청 헤더와 바디
				String.class);   // 응답 받을 타입
		return responseEntity.getBody();
	}

}
