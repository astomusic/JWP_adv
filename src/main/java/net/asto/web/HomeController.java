package net.asto.web;

import net.asto.domain.users.Authenticate;
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
		model.addAttribute("authenticate", new Authenticate());
		logger.info("homepage loading start");
		
		return "home";// /home.jps (prefix + name + suffix)
	}
}
