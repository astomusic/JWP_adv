package net.asto.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/")
	public String home() {
		// /home.jps (prefix + name + suffix)
		return "home";
	}
}
