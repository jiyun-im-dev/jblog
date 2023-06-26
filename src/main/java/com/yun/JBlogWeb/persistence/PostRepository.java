package com.yun.JBlogWeb.persistence;

import com.yun.JBlogWeb.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
