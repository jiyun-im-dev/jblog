package com.yun.JBlogWeb.security;

import com.yun.JBlogWeb.domain.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Getter
@Setter
public class UserDetailsImpl implements UserDetails, OAuth2User {

	// 구글에서 조회한 사용자 정보를 담을 컬렉션
	private Map<String, Object> attributes;

	private static final long serialVersionUID = 1L;
	private User user; // users 테이블과 매핑된 엔티티

	public UserDetailsImpl(User user) {
		this.user = user;
	}

	// OAuth 로그인 시 사용할 생성자
	public UserDetailsImpl(User user, Map<String, Object> attributes) {
		this.user = user;
		this.attributes = attributes;
	}

	// 구글에서 조회한 사용자 정보가 저장된 컬렉션 반환
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	// 이름은 사용하지 않는 정보이므로 null 반환
	@Override
	public String getName() {
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 계정이 가지고 있는 권한 목록 저장하여 반환
		Collection<GrantedAuthority> roleList = new ArrayList<>();

		//권한 목록 설정
		roleList.add(() -> {
			return "ROLE_" + user.getRole();
		});

		return roleList;
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public boolean isAccountNonExpired() {
		// 계정이 만료되지 않았는지
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// 계정이 잠겨있지 않은지
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// 비밀번호가 만료되지 않았는지
		return true;
	}

	@Override
	public boolean isEnabled() {
		// 계정이 활성화되었는지
		return true;
	}
}
