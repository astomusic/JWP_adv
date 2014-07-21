package net.asto.web;

import net.asto.domain.users.User;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	public static Logger logger = Logger.getLogger(HomeController.class);
	
	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("user", new User());
		logger.info("homepage loading start");
		// /home.jps (prefix + name + suffix)
		return "home";
	}
}
