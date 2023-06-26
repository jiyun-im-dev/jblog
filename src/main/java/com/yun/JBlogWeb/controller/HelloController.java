package com.yun.JBlogWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("username", "Hello 사용자");
		return "index";
	}

	@GetMapping("/jsp")
	public String jsp(Model model) {
		model.addAttribute("username", "모르는 사용자");
		return "hello"; // .jsp는 suffix로 등록해놨음
	}

	@GetMapping("/hello")
	public String hello() {
		return "redirect:hello.html";
	}

	@GetMapping("/image")
	public String image() {
		return "redirect:images/hachiware.webp";
	}

}
