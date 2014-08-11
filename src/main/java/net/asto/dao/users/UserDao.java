package net.asto.dao.users;

import net.asto.domain.users.User;

public interface UserDao {

	User findByEmail(String email);

	void create(User user);

	void update(User user);

}