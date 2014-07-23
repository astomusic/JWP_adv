package net.asto.web.users;

import java.util.List;

import javax.validation.Valid;

import net.asto.dao.users.UserDao;
import net.asto.domain.users.User;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	public static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="/users", method=RequestMethod.POST)
	public String create(@Valid User user, BindingResult bindingResult) {
		logger.info("User :" + user);

		if(bindingResult.hasErrors()) {
			logger.info("bindingResult has error");
			List<ObjectError> errors = bindingResult.getAllErrors();
			for (ObjectError error : errors) {
				logger.info("error : " + error.getDefaultMessage());
			}
			return "redirect:/";
		}
		
		userDao.create(user);
		logger.info("DataBase :" + userDao.findByEmail(user.getEmail()));
		return "redirect:/";
	}
}
