package com.yun.JBlogWeb.config;

import com.yun.JBlogWeb.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticateInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 세션에 회원 정보가 존재하는지 확인
		HttpSession session = request.getSession();

		// 세션에서 principal 속성(=사용자 정보)을 가져와서 Object에서 User로 타입 캐스팅
		User principal = (User) session.getAttribute("principal");
		if (principal == null) {
			// 로그인하지 않았을 때 응답: 로그인 페이지로 리다이렉트
			response.sendRedirect("/auth/login");
		}
		return true;
	}

}
