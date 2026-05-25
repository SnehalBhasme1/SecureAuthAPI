package com.learning.spring.jwt.repository;


	import java.util.Optional;
	import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.spring.jwt.entity.User;
	

	public interface UserRepository extends JpaRepository<User, Long> {
	    Optional<User> findByEmail(String email);
	}

