package net.asto.dao.users;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import net.asto.domain.users.User;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class UserDao extends JdbcDaoSupport {
	@PostConstruct
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("asto.sql"));
		DatabasePopulatorUtils.execute(populator, getDataSource());
	}

	public User findByEmail(String email) {
		String sql = "SELECT * FROM USERS WHERE email = ?";
		RowMapper<User> rowMapper = new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				return new User(rs.getString("email"), rs.getString("password"), rs.getString("passwordConfirm"));
			}
		};
		
		return getJdbcTemplate().queryForObject(sql, rowMapper, email);
	}

	public void create(User user) {
		String sql = "INSERT INTO USERS VALUES(?, ?, ?)";
		getJdbcTemplate().update(sql, user.getEmail(), user.getPassword(), user.getPasswordConfirm());
	}
	
	
	
}
