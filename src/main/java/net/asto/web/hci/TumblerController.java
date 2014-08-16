package net.asto.web.hci;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TumblerController {
	public static Logger logger = Logger.getLogger(TumblerController.class);

	@Autowired
	private TumblerDao tumblerDao;

	@RequestMapping(value = "/tumbler", method = RequestMethod.POST)
	public String update(@Valid Tumbler tumbler, BindingResult bindingResult) {
		logger.info("Tumbler :" + tumbler);

		tumblerDao.update(tumbler);
		return "redirect:/";
	}

	@RequestMapping("/ajax")
	public ModelAndView helloAjaxTest() {
		return new ModelAndView("ajax", "message", "Crunchify Spring MVC with Ajax and JQuery Demo..");
	}

	@RequestMapping(value = "/ajaxtest", method = RequestMethod.GET)
	public @ResponseBody String getWater(String id) {
		logger.info("Tumbler :" + id);
		Tumbler tumbler = tumblerDao.findByid(id);

		return tumbler.getWater();
	}

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public String select(String id, Model model) {
		logger.info("Tumbler :" + id);
		Tumbler tumbler = tumblerDao.findByid(id);
		model.addAttribute("water", tumbler.getWater());

		return "home";
	}

}
