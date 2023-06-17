package com.yun.JBlogWeb.controller;

import com.yun.JBlogWeb.domain.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class RESTController {

	// GET: SELECT
	@GetMapping("jblog")
	public User httpGet() {
		return User.builder()
				.id(1)
				.username("test")
				.password("testpwd")
				.email("test@email.com")
				.build();
	}

	// POST: INSERT
	@PostMapping("jblog")
	public String httpPost(@RequestBody User user) { // 컨트롤러 메서드의 파라미터로 도메인 객체를 받음
		return "POST 요청 처리 입력값: " + user.toString();
	}

	// PUT: UPDATE
	@PutMapping("jblog")
	public String httpPut(@RequestBody User user) {
		return "PUT 요청 처리 입력값: " + user.toString();
	}

	// DELETE: DELETE
	@DeleteMapping("jblog")
	public String httpDelete(@RequestParam int id) {
		return "DELETE 요청 처리 입력값: " + id;
	}

}
