package com.yun.JBlogWeb.persistence;

import com.yun.JBlogWeb.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	// SELECT * FROM users WHERE username = ?1;
	Optional<User> findByUsername(String username);

}
