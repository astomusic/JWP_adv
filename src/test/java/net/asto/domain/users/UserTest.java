package net.asto.domain.users;

import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserTest {
	public static Logger logger = Logger.getLogger(UserTest.class);
	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void userEmailIsEmpty() {
		User user = new User("", "password", "password");
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		assertEquals( 2, constraintViolations.size() );
		
		for (ConstraintViolation<User> constraintViolation : constraintViolations) {
			logger.info("Violation error msg : " + constraintViolation.getMessage());
		}
	}

	@Test
	public void matchPassword() {
		String password = "password";
		Authenticate authenticate = new Authenticate("email", password);
		User user = new User("", password, password);
		assertTrue(user.matchPassword(authenticate));
		
		authenticate = new Authenticate("email", "password2");
		assertFalse(user.matchPassword(authenticate));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void updateWhenMisMatchEmail() {
		User user = new User("email", "password", "password");
		User updateUser = new User("newEmail", "newPassword", "newPassword");
		user.update(updateUser);
	}

	@Test
	public void update() {
		User user = new User("email", "password", "password");
		User updateUser = new User("email", "newPassword", "newPassword");
		User updatedUser = user.update(updateUser);
		
		assertEquals(updatedUser, updateUser);
		
	}
}
