package net.asto.web.users;

import net.asto.domain.users.User;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	public static Logger logger = Logger.getLogger(UserController.class);
	
	@RequestMapping(value="/users", method=RequestMethod.POST)
	public String create(User user) {
		logger.info("User :" + user);
		return "home";
	}
}
