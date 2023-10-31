package com.yun.JBlogWeb.domain;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
// User 도메인 클래스
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 1부터 시작, 자동으로 1씩 증가
	private int id;

	@Column(nullable = false, length = 50, unique = true)
	private String username;

	@Column(length = 100)
	private String password;

	@Column(nullable = false, length = 100)
	private String email;

	@Enumerated(EnumType.STRING)
	private Role role;

	@CreationTimestamp // 현재 시간을 기본값으로 등록
	private Timestamp createDate;

	@Enumerated(EnumType.STRING)
	private OAuthType oAuthType;

}
