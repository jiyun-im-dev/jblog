package com.yun.JBlogWeb.controller;

import com.yun.JBlogWeb.domain.Post;
import com.yun.JBlogWeb.domain.User;
import com.yun.JBlogWeb.dto.ResponseDto;
import com.yun.JBlogWeb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class PostController {

	@Autowired
	private PostService postService;

	@GetMapping("/post/insertPost")
	public String insertPost() {
		return "post/insertPost";
	}

	@PostMapping("/post/insertPost")
	public @ResponseBody ResponseDto<?> insertPost(@RequestBody Post post, HttpSession session) {
		// Post 객체를 영속화(리포지토리에 저장)하기 전 연관된 User 엔티티 설정
		User principal = (User) session.getAttribute("principal");
		post.setUser(principal);

		postService.insertPost(post);

		return new ResponseDto<>(HttpStatus.OK.value(), "새로운 포스트를 등록했습니다.");
	}

	@GetMapping({"", "/"})
	public String index() {
		return "index";
	}

}
