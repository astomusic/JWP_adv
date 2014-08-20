package net.asto.dao.users;

import java.util.List;

import net.asto.domain.users.User;

public interface UserDao {
	
	List<User> findAll();

	User findByEmail(String email);

	void create(User user);

	void update(User user);

}