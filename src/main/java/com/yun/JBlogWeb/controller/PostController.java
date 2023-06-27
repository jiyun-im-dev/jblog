package com.yun.JBlogWeb.controller;

import com.yun.JBlogWeb.domain.Post;
import com.yun.JBlogWeb.domain.User;
import com.yun.JBlogWeb.dto.ResponseDto;
import com.yun.JBlogWeb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
	public String getPostList(Model model, HttpSession session, @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		// 가장 최근에 등록된 포스트부터 출력
		model.addAttribute("postList", postService.getPostList(pageable));
		model.addAttribute("username", session.getAttribute("principal").toString());
		return "index";
	}

	@GetMapping("/post/{id}")
	public String getPost(@PathVariable int id, Model model) {
		model.addAttribute("post", postService.getPost(id));
		return "post/getPost";
	}

}
