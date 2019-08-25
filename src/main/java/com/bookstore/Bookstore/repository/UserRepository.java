package com.bookstore.Bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.Bookstore.entity.User;

public interface UserRepository extends CrudRepository<User,Long>{
	
	User findByUsername(String username);
	
	User findByEmail(String email);

}
