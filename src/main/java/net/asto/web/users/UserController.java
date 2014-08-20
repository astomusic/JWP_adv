package net.asto.web.users;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.asto.dao.users.UserDao;
import net.asto.domain.users.Authenticate;
import net.asto.domain.users.ChangeUser;
import net.asto.domain.users.User;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	public static Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public String create(@Valid User user, BindingResult bindingResult, Model model) {
		logger.info("User :" + user);

		if (bindingResult.hasErrors()) {
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

	@RequestMapping(value = "/users/login", method = RequestMethod.POST)
	public String login(@Valid Authenticate authenticate, BindingResult bindingResult, HttpSession session, Model model) {
		if (bindingResult.hasErrors()) {
			logger.info("bindingResult has error");
			List<ObjectError> errors = bindingResult.getAllErrors();
			for (ObjectError error : errors) {
				logger.info("error : " + error.getDefaultMessage());
				this.setErrorMessage(error.getDefaultMessage(), model);
			}
			return "home";
		}

		User user = userDao.findByEmail(authenticate.getEmail());

		if (user == null) {
			this.setErrorMessage("존재하지 않는 사용자 입니다.", model);
			return "home";
		}

		if (!user.matchPassword(authenticate)) {
			this.setErrorMessage("비밀번호가 틀립니다.", model);
			return "home";
		}

		// TODO 세션에 사용자 정보를 저장
		session.setAttribute("email", user.getEmail());

		return "redirect:/";
	}

	@RequestMapping(value = "/users/modify/{userEmail:.+}", method = RequestMethod.PUT)
	public String update(@PathVariable String userEmail, @Valid ChangeUser changeUser, BindingResult bindingResult, Model model, HttpSession session) {
		logger.info("ChangeUser :" + changeUser);

		Object email = session.getAttribute("email");

		if (email == null) {
			this.setErrorMessage("로그인 상태가 아닙니다.", model);
			return "home";
		} else if (!userEmail.equals((String) email)) {
			this.setErrorMessage("해당 유저가 아닙니다.", model);
			return "home";
		}

		User user = userDao.findByEmail((String) email);

		if (bindingResult.hasErrors()) {
			logger.info("bindingResult has error");
			List<ObjectError> errors = bindingResult.getAllErrors();
			for (ObjectError error : errors) {
				logger.info("error : " + error.getDefaultMessage());
				this.setErrorMessage(error.getDefaultMessage(), model);
			}
			return "home";
		}

		if (!changeUser.getOldPassword().equals(user.getPassword())) {
			this.setErrorMessage("비밀번호가 틀립니다.", model);
			return "home";
		}

		user.setPassword(changeUser.getNewPassword());
		user.setPasswordConfirm(changeUser.getNewPasswordConfirm());

		userDao.update(user);
		logger.info("DataBase :" + userDao.findByEmail(user.getEmail()));
		return "redirect:/";
	}

	@RequestMapping(value = "/users/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("email");
		return "redirect:/";
	}

	@RequestMapping("/userList")
	public String list(Model model) {
		List<User> userList = userDao.findAll();
		model.addAttribute("userList", userList);
		return "home";
	}

	public void setErrorMessage(String errorMessage, Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("authenticate", new Authenticate());
		model.addAttribute("errorMessage", errorMessage);
		model.addAttribute("ChangeUser", new ChangeUser());
	}
}
