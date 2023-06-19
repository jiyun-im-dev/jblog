package com.yun.JBlogWeb.persistence;

import com.yun.JBlogWeb.domain.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

	// 스프링-마이바티스 연동 위해 SqlSessionFactoryBean과 SqlsessionTemplate 객체 필요
	@Autowired
	private SqlSessionTemplate mybatis;

	public void insertUser(User user) {
		mybatis.insert("insertUser", user);
		// 매퍼 인터페이스에 등록한 메서드명으로 실행할 SQL문을 식별
	}

	public void updateUser(User user) {
		mybatis.update("updateUser", user);
	}

	public void deleteUser(User user) {
		mybatis.delete("deleteUser", user);
	}

	public User getUser(User user) {
		return mybatis.selectOne("getUser", user);
	}

	public List<User> getUserList() {
		return mybatis.selectList("getUserList");
	}

}
