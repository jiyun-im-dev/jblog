package com.yun.JBlogWeb.controller;

import com.yun.JBlogWeb.domain.Reply;
import com.yun.JBlogWeb.domain.User;
import com.yun.JBlogWeb.dto.ResponseDto;
import com.yun.JBlogWeb.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

// 컨트롤러로 비즈니스 컴포넌트(서비스 등) 호출
@Controller
public class ReplyController {

	@Autowired
	private ReplyService replyService;

	@PostMapping("/reply/{postId}")
	public @ResponseBody ResponseDto<?> insertReply(@PathVariable int postId,
													@RequestBody Reply reply, HttpSession session) {
		User principal = (User) session.getAttribute("principal");
		replyService.insertReply(postId, reply, principal);
		return new ResponseDto<>(HttpStatus.OK.value(), postId + "번 포스트에 댓글이 등록됐습니다.");
	}

	@DeleteMapping("/reply/{replyId}")
	public @ResponseBody ResponseDto<?> deleteReply(@PathVariable int replyId) {
		replyService.deleteReply(replyId);
		return new ResponseDto<>(HttpStatus.OK.value(), replyId + "번 댓글이 삭제됐습니다.");
	}

}
