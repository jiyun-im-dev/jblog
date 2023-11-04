package com.yun.JBlogWeb.security;

import com.yun.JBlogWeb.domain.OAuthType;
import com.yun.JBlogWeb.domain.Role;
import com.yun.JBlogWeb.domain.User;
import com.yun.JBlogWeb.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class OAuth2UserDetailsServiceImpl extends DefaultOAuth2UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Value("${google.default.password}")
	private String googlePassword;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		// 액세스 토큰이 저장된 userRequest를 이용하여 구글로부터 회원 정보 받아옴
		OAuth2User oAuth2User = super.loadUser(userRequest);
		// 구글이 전달한 정보를 바탕으로 회원 정보 구성
		String providerId = oAuth2User.getAttribute("sub");
		String email = oAuth2User.getAttribute("email");
		String username = email + "_" + providerId;
		String password = passwordEncoder.encode(googlePassword);
		// 회원가입이 되어있는 사용자인지 확인
		User findUser = userRepository.findByUsername(username).orElseGet(User::new);
		if (findUser.getUsername() == null) {
			findUser = User.builder()
					.username(username)
					.password(password)
					.email(email)
					.role(Role.USER)
					.oAuthType(OAuthType.GOOGLE)
					.build();
		}
		// OAuth2 Client가 UserDetailsImpl에 설정된 정보로
		// Authentication 객체를 SecurityContext에 자동 등록함
		return new UserDetailsImpl(findUser, oAuth2User.getAttributes());
	}
}
