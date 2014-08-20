package net.asto.dao.users;

import java.util.List;

import net.asto.domain.users.User;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MybatisUserDao implements UserDao {
	@Autowired
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

	@Override
	public List<User> findAll() {
		return sqlSession.selectList("UserMapper.findAll");
	}

}
