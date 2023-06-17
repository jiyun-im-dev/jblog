package com.yun.JBlogWeb.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// User 도메인 클래스
public class User {

	private int id;

	private String username;

	private String password;

	private String email;

}
