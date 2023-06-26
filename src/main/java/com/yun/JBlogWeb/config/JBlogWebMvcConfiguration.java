package com.yun.JBlogWeb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class JBlogWebMvcConfiguration implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 인덱스 페이지 경로를 인증이 필요한 경로로 등록
		// 블로그(인덱스 페이지)에 접속하는 순간 preHandle() 메서드가 동작
		registry.addInterceptor(new AuthenticateInterceptor()).addPathPatterns("/");
	}

}
