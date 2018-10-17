package com.amit.cruddemobackend.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.amit.cruddemobackend.DAO.UserDAO;
import com.amit.cruddemobackend.model.User;

public class UserDAOTest {

	private static AnnotationConfigApplicationContext context=null;
	private static UserDAO userDAO=null;
	private static User user=null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context=new AnnotationConfigApplicationContext();
		context.scan("com.amit.cruddemobackend");
		context.refresh();
		userDAO=(UserDAO)context.getBean("userDAO");
		user=new User();
	}
	
	@Test
	public void testRegisterUser() {
		user.setEmail("amitsingh@gmail.com");
		user.setPassword("123456");
		user.setFirstname("amit");
		user.setLastname("singh");
		user.setMobile("9417808375");
		assertTrue("User record not inserted",userDAO.saveUser(user));
	}
	@Ignore
	@Test
	public void testUpdateUser() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testDeleteUser() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testGetUserByEmail() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testGetAllActiveUser() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testActiveOrdeactiveUser() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testValidate() {
		fail("Not yet implemented");
	}

}


