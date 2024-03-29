package com.bookstore.Bookstore.service;

import java.util.Set;

import com.bookstore.Bookstore.entity.User;
import com.bookstore.Bookstore.security.PasswordResetToken;
import com.bookstore.Bookstore.security.UserRole;

public interface UserService {
	
	PasswordResetToken getPasswordResetToken(final String token);
	
	void createPasswordResetTokenForUser(final User user, final String token);

	User findByUsername(String username);
	
	User findByEmail(String email);

	User createUser(User user, Set<UserRole> userRole)throws Exception;

	User save(User user);
	
}
