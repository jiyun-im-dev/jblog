package com.yun.JBlogWeb.service;

import com.yun.JBlogWeb.domain.Post;
import com.yun.JBlogWeb.domain.Reply;
import com.yun.JBlogWeb.domain.User;
import com.yun.JBlogWeb.persistence.PostRepository;
import com.yun.JBlogWeb.persistence.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReplyService {

	@Autowired
	PostRepository postRepository;

	@Autowired
	ReplyRepository replyRepository;

	@Transactional
	public void insertReply(int postId, Reply reply, User user) {
		Post post = postRepository.findById(postId).get();
		reply.setUser(user);
		reply.setPost(post);
		replyRepository.save(reply);
	}

	@Transactional
	public void deleteReply(int replyId) {
		replyRepository.deleteById(replyId);
	}

}
