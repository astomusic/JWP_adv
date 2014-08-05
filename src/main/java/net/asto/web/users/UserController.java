package net.asto.web.users;

import java.util.List;

import javax.validation.Valid;

import net.asto.dao.users.UserDao;
import net.asto.domain.users.Authenticate;
import net.asto.domain.users.User;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String create(@Valid User user, BindingResult bindingResult, Model model) {
		logger.info("User :" + user);

		if(bindingResult.hasErrors()) {
			logger.info("bindingResult has error");
			List<ObjectError> errors = bindingResult.getAllErrors();
			for (ObjectError error : errors) {
				logger.info("error : " + error.getDefaultMessage());
				this.setErrorMessage(error.getDefaultMessage(), model);
			}
			return "home";
		}
		
		userDao.create(user);
		logger.info("DataBase :" + userDao.findByEmail(user.getEmail()));
		return "redirect:/";
	}
	
	
	@RequestMapping(value="/users/login", method=RequestMethod.POST)
	public String login(@Valid Authenticate authenticate, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			logger.info("bindingResult has error");
			List<ObjectError> errors = bindingResult.getAllErrors();
			for (ObjectError error : errors) {
				logger.info("error : " + error.getDefaultMessage());
				this.setErrorMessage(error.getDefaultMessage(), model);
			}
			return "home";
		}
		
		User user = userDao.findByEmail(authenticate.getEmail());
		
		if(user == null) {
			this.setErrorMessage("존재하지 않는 사용자 입니다.", model);
			return "home";
		}
		
		if(!user.matchPassword(authenticate)) {
			this.setErrorMessage("비밀번호가 틀립니다.", model);
			return "home";
		}
		
		//TODO 세션에 사용자 정보를 저장
		
		return "redirect:/";
	}
	
	public void setErrorMessage(String errorMessage, Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("authenticate", new Authenticate());
		model.addAttribute("errorMessage", errorMessage);
	}
}
