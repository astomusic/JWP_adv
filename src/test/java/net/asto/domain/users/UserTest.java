package net.asto.domain.users;

import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.AssertTrue;

import net.asto.domain.users.User;

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
}
