
package controllers.poller;

import java.util.Calendar;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.PollService;
import services.PollerService;
import services.ValidPeriodService;
import domain.Poll;
import domain.Poller;

@Controller
@RequestMapping("/poll/poller")
public class PollPollerController {

	@Autowired
	private PollerService		pollerService;

	@Autowired
	private PollService			pollService;

	@Autowired
	private ValidPeriodService	validPeriodService;
	@Autowired
	private LoginService		loginService;


	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView res;

		res = new ModelAndView("poll/list");

		final Poller poller = this.pollerService.findActorByUsername(LoginService.getPrincipal().getId());

		res.addObject("poll", poller.getPolls());
		res.addObject("actualDate", Calendar.getInstance().getTime());
		res.addObject("validPeriod", this.validPeriodService.get().getMinimumPeriod());
		res.addObject("isPoller", true);

		return res;
	}

	@RequestMapping("/remove")
	public ModelAndView remove(@RequestParam final Integer q) {

		try {
			final Poll poll = this.pollService.findOne(q);
			final Poller pollerlogin = (Poller) this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
			Assert.isTrue(pollerlogin.getPolls().contains(poll));
			this.pollService.delete(poll);
			return this.list();
		} catch (final Exception e) {
			return this.list();
		}

	}

	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam final Integer q) {
		ModelAndView res;

		res = new ModelAndView("poll/edit");
		try {
			final Poll poll = this.pollService.findOne(q);
			final Poller pollerlogin = (Poller) this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
			Assert.isTrue(pollerlogin.getPolls().contains(poll));
			res.addObject("poll", poll);
		} catch (final Throwable e) {
			res = new ModelAndView("redirect:/welcome/index.do");
		}

		return res;

	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Poll poll, final BindingResult binding) {
		ModelAndView res = null;

		if (binding.hasErrors()) {
			res = new ModelAndView("poll/edit");
			res.addObject("poll", poll);
		} else
			try {

				final Date d = new Date();
				d.setHours(0);
				d.setMinutes(0);
				d.setSeconds(0);

				if (poll.getStartDate().before(d)) {
					binding.rejectValue("startDate", "error.startDate1", "error");
					throw new IllegalArgumentException();
				}

				else if (poll.getStartDate().after(poll.getEndDate())) {
					binding.rejectValue("startDate", "error.startDate", "error");
					throw new IllegalArgumentException();
				}

				this.pollService.update(poll);
				res = new ModelAndView("redirect:list.do");
				res.addObject("poll", poll);
			} catch (final Exception e) {
				res = new ModelAndView("poll/edit");
				res.addObject("poll", poll);
			}
		return res;
	}

}
