package net.asto.web.users;

import net.asto.dao.users.UserDao;
import net.asto.domain.users.User;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	public static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="/users", method=RequestMethod.POST)
	public String create(User user) {
		logger.info("User :" + user);
		userDao.create(user);
		logger.info("DataBase :" + userDao.findByEmail(user.getEmail()));
		return "home";
	}
}
