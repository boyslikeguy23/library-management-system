package com.rico.test.db;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.entity.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserEntityTest {

	@Autowired
	private TestEntityManager entityManager;
	
	private User adminUser, visitorUser;
	
	@Before
	public void setUp() {
		adminUser = new AdminUser();
		adminUser.setUserName("Administrator");
		adminUser.setPassword("password");
		
		visitorUser = new VisitorUser();
		visitorUser.setUserName("John");
		visitorUser.setPassword("password");
	}
	
	@Test
	public void createNewVisitorUserTest() {
		User vistorUserData = this.entityManager.persist(visitorUser);
		assertTrue((vistorUserData.getUserName() == "John") && (vistorUserData.getType() == "VISITOR"));
	}
	
	@Test
	public void createNewAdminUserTest() {
		User adminUserData = this.entityManager.persist(adminUser);
		assertTrue((adminUserData.getUserName() == "Administrator") && (adminUserData.getType() == "ADMIN"));
	}

}
