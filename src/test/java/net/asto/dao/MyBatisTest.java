package net.asto.dao;

import static org.junit.Assert.*;

import java.io.InputStream;

import net.asto.domain.users.User;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class MyBatisTest {
	public static Logger logger = Logger.getLogger(MyBatisTest.class);
	private SqlSessionFactory sqlSessionFactory;
	
	@Before
	public void setup() throws Exception {
		String resource = "mybatis-config-test.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	@Test
	public void gettingStarted() {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			User user = session.selectOne("UserMapper.findByEmail", "asto@as.com");
			logger.info("User :" + user);
		} //java.os.closable을 구현하고 있는 경우 finally 없이 위와같이 리소스 반환을 자동화 할수 있다. 
	}

	@Test
	public void insert() throws Exception {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			User user = new User("as@as.com", "1111", "1111");
			session.insert("UserMapper.create", user);
			User actual = session.selectOne("UserMapper.findByEmail", user.getEmail());
			assertEquals(user, actual);
		} 
	}
	
}
