package net.asto.dao.users;

import net.asto.domain.users.User;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

public class MybatisUserDao implements UserDao {
	public static Logger logger = Logger.getLogger(MybatisUserDao.class);
	
	private SqlSession sqlSession;
	
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
