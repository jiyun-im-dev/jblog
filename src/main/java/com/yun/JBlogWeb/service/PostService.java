package com.yun.JBlogWeb.service;

import com.yun.JBlogWeb.domain.Post;
import com.yun.JBlogWeb.persistence.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	@Transactional
	public void insertPost(Post post) {
		post.setCnt(0);
		postRepository.save(post);
	}

	@Transactional(readOnly = true)
	public List<Post> getPostList() {
		return postRepository.findAll();
	}

}
