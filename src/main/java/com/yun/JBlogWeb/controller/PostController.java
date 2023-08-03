package com.yun.JBlogWeb.controller;

import com.yun.JBlogWeb.domain.Post;
import com.yun.JBlogWeb.domain.User;
import com.yun.JBlogWeb.dto.PostDto;
import com.yun.JBlogWeb.dto.ResponseDto;
import com.yun.JBlogWeb.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/post/insertPost")
	public String insertPost() {
		return "post/insertPost";
	}

	@PostMapping("/post/insertPost")
	public @ResponseBody ResponseDto<?> insertPost(
			@Valid @RequestBody PostDto postDto, BindingResult bindingResult, HttpSession session) {
		if (bindingResult.hasErrors()) {
			// 에러가 하나라도 있다면 에러 메시지를 Map에 등록
			Map<String, String> errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), errorMap);
		}

		Post post = modelMapper.map(postDto, Post.class);

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

	@GetMapping("/post/updatePost/{id}")
	public String updatePost(@PathVariable int id, Model model) {
		model.addAttribute("post", postService.getPost(id));
		return "post/updatePost";
	}

	@PutMapping("/post")
	public @ResponseBody ResponseDto<?> updatePost(@RequestBody Post post) {
		postService.updatePost(post);
		return new ResponseDto<>(HttpStatus.OK.value(), post.getId() + "번 포스트를 수정했습니다.");
	}

	@DeleteMapping("/post/{id}")
	public @ResponseBody ResponseDto<?> deletePost(@PathVariable int id) {
		postService.deletePost(id);
		return new ResponseDto<>(HttpStatus.OK.value(), id + "번 포스트를 삭제했습니다.");
	}

}
