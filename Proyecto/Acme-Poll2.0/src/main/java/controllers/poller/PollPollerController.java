package controllers.poller;

import java.util.Calendar;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Poll;
import domain.Poller;
import security.LoginService;
import services.PollService;
import services.PollerService;
import services.ValidPeriodService;

@Controller
@RequestMapping("/poll/poller")
public class PollPollerController {

	@Autowired
	private PollerService pollerService;

	@Autowired
	private PollService pollService;
	
	@Autowired
	private ValidPeriodService	validPeriodService;


	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView res;

		res = new ModelAndView("poll/list");

		Poller poller = pollerService.findActorByUsername(LoginService.getPrincipal().getId());

		res.addObject("poll", poller.getPolls());
		res.addObject("actualDate", Calendar.getInstance().getTime());
		res.addObject("validPeriod", validPeriodService.get().getMinimumPeriod());

		return res;
	}


	@RequestMapping("/remove")
	public ModelAndView remove(@RequestParam Integer q) {

		try {
			Poll poll = pollService.findOne(q);
			pollService.delete(poll);
			return list();
		}catch (Exception e) {
			return list();
		}

	}

	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam Integer q) {
		ModelAndView res;

		res = new ModelAndView("poll/edit");

		Poll poll = pollService.findOne(q);

		res.addObject("poll", poll);

		return res;

	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Poll poll,BindingResult binding) {
		ModelAndView res= null;

		if (binding.hasErrors()) {
			res = new ModelAndView("poll/edit");
			res.addObject("poll", poll);
		} else {
			try {
				pollService.update(poll);
				res = new ModelAndView("poll/edit");
				res.addObject("poll", poll);
			}catch (Exception e) {
				res = new ModelAndView("poll/edit");
				res.addObject("poll", poll);
			}
		}
		return res;
	}

}
