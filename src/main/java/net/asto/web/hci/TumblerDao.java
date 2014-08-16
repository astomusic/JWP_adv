package net.asto.web.hci;

import net.asto.dao.users.MybatisUserDao;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

public class TumblerDao {
	public static Logger logger = Logger.getLogger(MybatisUserDao.class);
	
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public Tumbler findByid(String id) {
		return sqlSession.selectOne("UserMapper.TumblerSelect", id);
	}

	public void update(Tumbler tumbler) {
		sqlSession.insert("UserMapper.TumblerUpdate", tumbler);

	}

}
