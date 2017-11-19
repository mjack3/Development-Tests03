package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.ValidPeriod;
import services.AdministratorService;
import services.ValidPeriodService;

@RequestMapping("/validPeriod/administrator")
@Controller
public class ValidPeriodAdministratorController {

	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private ValidPeriodService validPeriodService;
	
	
	@RequestMapping("/edit")
	public ModelAndView list() {
		ModelAndView res;

		res = new ModelAndView("validPeriod/edit");
		
		res.addObject("validPeriod", validPeriodService.get());

		return res;
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid ValidPeriod validPeriod, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = new ModelAndView("validPeriod/edit");			
			result.addObject("validPeriod", validPeriod);
		} else
			try {
				this.validPeriodService.save(validPeriod);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (final Throwable th) {
				result = new ModelAndView("validPeriod/edit");			
				result.addObject("validPeriod", validPeriod);
			}
		return result;
	}
	
}
