package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Instance;
import services.InstanceService;

@Controller
@RequestMapping("/instance")
public class InstanceController {
	
	@Autowired
	private InstanceService instanceService;

	private Integer toSave;
	
	@RequestMapping("/toSearch")
	public ModelAndView toSearch() {
		ModelAndView res;

		res = new ModelAndView("instance/toSearch");

		return res;
	}
	
	@RequestMapping("/redirect")
	public ModelAndView redirect() {
		ModelAndView res;

		res = new ModelAndView("instance/edit");
		res.addObject("instance", instanceService.findOne(toSave));

		return res;
	}
	
	@RequestMapping("/search")
	public ModelAndView search(@RequestParam String q) {
		ModelAndView res;

		res = new ModelAndView("instance/edit");
		res.addObject("instance", instanceService.findByTicker(q));
		toSave=instanceService.findByTicker(q).getId();
		
		return res;
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Instance instance,BindingResult binding) {
		if (binding.hasErrors()) {
			return search(instance.getTicker());
		} else {
			try {
				instanceService.update(instance);
				return new ModelAndView("redirect:welcome/index.do");
			}catch (Exception e) {
				return search(instance.getTicker());
			}
		}
	}
}
