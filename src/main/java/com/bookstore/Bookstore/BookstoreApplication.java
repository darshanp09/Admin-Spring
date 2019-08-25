package com.bookstore.Bookstore;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.bookstore.Bookstore.entity.User;
import com.bookstore.Bookstore.security.Role;
import com.bookstore.Bookstore.security.UserRole;
import com.bookstore.Bookstore.service.UserService;
import com.bookstore.Bookstore.utility.SecurityUtility;

@SpringBootApplication
public class BookstoreApplication implements CommandLineRunner{
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
    public SpringSecurityDialect securityDialect() {

        return new SpringSecurityDialect();

    }
	
	@Bean
	public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver, SpringSecurityDialect sec) {

	    final SpringTemplateEngine templateEngine = new SpringTemplateEngine();

	    templateEngine.setTemplateResolver(templateResolver);

	    templateEngine.addDialect(sec);

	    return templateEngine;

	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		User user = new User();
		user.setFirstName("John");
		user.setLastName("Adams");
		user.setUsername("j");
		user.setPassword(SecurityUtility.passwordEncoder().encode("p"));
		user.setEmail("JAdams@gmail.com");
		Set<UserRole> userRoles = new HashSet<>();
		Role role = new Role();
		role.setRoleId(1);
		role.setName("ROLE_USER");
		
		userRoles.add(new UserRole(user, role));
		
		userService.createUser(user,userRoles);
		
		
	}

}
