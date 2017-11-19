package controllers.poller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Poll;
import services.PollService;

@Controller
@RequestMapping("/hint/poller")
public class HintPollerController {

	@Autowired
	private PollService pollService;

	
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam Integer q) {
		ModelAndView res;

		res = new ModelAndView("hint/list");
		
		Poll poll = pollService.findOne(q);
		
		res.addObject("hint", poll.getHints());
		res.addObject("pollId", q);

		return res;
	}
	
	
}
