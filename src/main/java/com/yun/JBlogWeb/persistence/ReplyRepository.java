package com.yun.JBlogWeb.persistence;


import com.yun.JBlogWeb.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
}
