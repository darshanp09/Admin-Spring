package com.bookstore.Bookstore.service.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.Bookstore.entity.User;
import com.bookstore.Bookstore.repository.PasswordResetTokenRepository;
import com.bookstore.Bookstore.repository.RoleRepository;
import com.bookstore.Bookstore.repository.UserRepository;
import com.bookstore.Bookstore.security.PasswordResetToken;
import com.bookstore.Bookstore.security.UserRole;
import com.bookstore.Bookstore.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public PasswordResetToken getPasswordResetToken(String token) {
		// TODO Auto-generated method stub
		return passwordResetTokenRepository.findByToken(token);
	}

	@Override
	public void createPasswordResetTokenForUser(User user, String token) {
		// TODO Auto-generated method stub
		final PasswordResetToken myToken = new PasswordResetToken(token, user);
		passwordResetTokenRepository.save(myToken);
		
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}

	@Override
	public User createUser(User user, Set<UserRole> userRole) throws Exception {
		User localUser = userRepository.findByUsername(user.getUsername());
		if(localUser !=null) {
			LOG.info("User {} already exists",user.getUsername());
		}else {
			for(UserRole ur :userRole) {
				roleRepository.save(ur.getRole());
			}
			user.getUserRole().addAll(userRole);
			
			localUser = userRepository.save(user);
		}
		return localUser;
	}

	@Override
	public User save(User user) {
		
		return userRepository.save(user);
	}

}
