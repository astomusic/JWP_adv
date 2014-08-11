package net.asto.dao.users;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import net.asto.domain.users.User;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class JdbcUserDao extends JdbcDaoSupport implements UserDao {
	public static Logger logger = Logger.getLogger(JdbcUserDao.class);
	@PostConstruct
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("asto.sql"));
		DatabasePopulatorUtils.execute(populator, getDataSource());
		logger.info("database initialized success");
	}

	/* (non-Javadoc)
	 * @see net.asto.dao.users.IUserDao#findByEmail(java.lang.String)
	 */
	@Override
	public User findByEmail(String email) {
		String sql = "SELECT * FROM USERS WHERE email = ?";
		RowMapper<User> rowMapper = new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				return new User(rs.getString("email"), rs.getString("password"), rs.getString("passwordConfirm"));
			}
		};
		try {
			return getJdbcTemplate().queryForObject(sql, rowMapper, email);
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
		
	}

	/* (non-Javadoc)
	 * @see net.asto.dao.users.IUserDao#create(net.asto.domain.users.User)
	 */
	@Override
	public void create(User user) {
		String sql = "INSERT INTO USERS VALUES(?, ?, ?)";
		getJdbcTemplate().update(sql, user.getEmail(), user.getPassword(), user.getPasswordConfirm());
	}

	/* (non-Javadoc)
	 * @see net.asto.dao.users.IUserDao#update(net.asto.domain.users.User)
	 */
	@Override
	public void update(User user) {
		String sql = "UPDATE USERS SET password = ?, passwordConfirm = ? WHERE email = ?";
		getJdbcTemplate().update(sql, user.getPassword(), user.getPasswordConfirm(), user.getEmail());		
	}
	
	
	
}
