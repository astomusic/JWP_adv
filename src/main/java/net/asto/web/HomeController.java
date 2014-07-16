package net.asto.web;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	public static Logger logger = Logger.getLogger(HomeController.class);
	
	@RequestMapping("/")
	public String home() {
		logger.info("homepage loading start");
		// /home.jps (prefix + name + suffix)
		return "home";
	}
}
