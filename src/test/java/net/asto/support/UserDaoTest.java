package net.asto.support;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.asto.dao.users.UserDao;
import net.asto.domain.users.User;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/applicationContext.xml")
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional
public class UserDaoTest {
	public static Logger logger = Logger.getLogger(UserDaoTest.class);
	@Autowired
	private UserDao userDao;
	
	@Test
	public void findByEmail() {
		User user = userDao.findByEmail("asto@as.com");
		logger.info("user :" + user);
	}
	
	@Test
	public void create() throws Exception {
		User user = new User("music11@as.com", "test", "test");
		userDao.create(user);
		User actual = userDao.findByEmail(user.getEmail());
		
		assertThat(actual, is(user));
	}
}
