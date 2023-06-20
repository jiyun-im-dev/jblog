package com.yun.JBlogWeb.controller;

import com.yun.JBlogWeb.domain.Role;
import com.yun.JBlogWeb.domain.User;
import com.yun.JBlogWeb.exception.JBlogException;
import com.yun.JBlogWeb.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

	@GetMapping("/auth/insertUser")
	public String insertUser() {
		return "user/insertUser";
	}

}
