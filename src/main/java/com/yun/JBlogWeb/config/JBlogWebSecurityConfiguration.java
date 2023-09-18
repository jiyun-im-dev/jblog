package com.yun.JBlogWeb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class JBlogWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 인증 없이 접근을 허용하는 경로
		http.authorizeRequests().antMatchers("/webjars/**", "/js/**", "/image/**", "/", "/auth/**")
				.permitAll();
		// 나머지 경로는 인증 필요
		http.authorizeRequests().anyRequest().authenticated();

		// CSRF 토큰을 받지 않음
		http.csrf().disable();
		// 사용자 정의 로그인 화면 제공
		http.formLogin().loginPage("/auth/securitylogin");

		// 로그아웃 설정
		http.logout().logoutUrl("/auth/logout").logoutSuccessUrl("/");
	}
}
