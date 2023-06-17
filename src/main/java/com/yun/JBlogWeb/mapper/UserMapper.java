package com.yun.JBlogWeb.mapper;

import com.yun.JBlogWeb.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

// MyBatis 사용한 매퍼 인터페이스
@Mapper
public interface UserMapper {

	@Insert("INSERT INTO users(id, username, password, email) " +
			"VALUES((SELECT NVL(MAX(id), 0) + 1 FROM users), #{username}, #{password}, #{email})")
	public void insertUser(User user);



}
