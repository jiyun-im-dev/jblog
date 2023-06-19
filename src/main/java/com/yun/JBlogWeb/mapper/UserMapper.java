package com.yun.JBlogWeb.mapper;

import com.yun.JBlogWeb.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

// MyBatis 사용한 매퍼 인터페이스
@Mapper
public interface UserMapper {

	// MyBatis의 애노테이션 이용하여 SQL 구문 등록
	@Insert("INSERT INTO users(id, username, password, email) " +
			"VALUES((SELECT NVL(MAX(id), 0) + 1 FROM users), #{username}, #{password}, #{email})")
	public void insertUser(User user);

	@Update("UPDATE users password = #{password}, email = #{email} WHERE id = #{id}")
	public void updateUser(User user);

	@Delete("DELETE users WHERE id = #{id}")
	public void deleteUser(User user);

	@Select("SELECT * FROM users WHERE username = #{username}")
	public void getUser(User user);

	@Select("SELECT * FROM users ORDER BY username DESC")
	public List<User> getUserList();

}
