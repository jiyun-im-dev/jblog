package com.yun.JBlogWeb.persistence;

import com.yun.JBlogWeb.domain.User;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDaoTest {

	@Autowired
	private UserDao userDao;

	User createTestUser() {
		User user = new User();
		user.setUsername("test");
		user.setPassword("testpwd");
		user.setEmail("test@email.com");
		return user;
	}

	@Test
	@DisplayName("회원 목록 조회 테스트")
	void getUserListTest() {
		User user = createTestUser();
		int before = userDao.getUserList().size();
		userDao.insertUser(user);
		int after = userDao.getUserList().size();

		assertEquals(before + 1, after);
	}

}