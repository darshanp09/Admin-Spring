package com.bookstore.Bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.Bookstore.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
	
	Role findByName(String name);

}
