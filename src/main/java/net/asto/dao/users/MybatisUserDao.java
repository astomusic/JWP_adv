package net.asto.dao.users;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import net.asto.domain.users.User;

public class MybatisUserDao implements UserDao {
	public static Logger logger = Logger.getLogger(MybatisUserDao.class);
	
	private SqlSession sqlSession;
	
	private DataSource dataSource;
	
	@PostConstruct
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("asto.sql"));
		DatabasePopulatorUtils.execute(populator, dataSource);
		logger.info("database initialized success");
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public User findByEmail(String email) {
		return sqlSession.selectOne("UserMapper.findByEmail", email);
	}

	@Override
	public void create(User user) {
		sqlSession.insert("UserMapper.create", user);

	}

	@Override
	public void update(User user) {
		sqlSession.insert("UserMapper.update", user);

	}

}
