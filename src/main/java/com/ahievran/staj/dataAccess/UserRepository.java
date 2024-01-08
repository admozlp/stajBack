package com.ahievran.staj.dataAccess;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahievran.staj.entities.User;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Long>{
	@Transactional  
	Optional<User> findByEmail(String email);
	  
	  boolean existsByEmail(String email);
}
