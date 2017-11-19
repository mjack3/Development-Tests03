package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Hint;
import domain.Poll;
import services.HintService;
import services.PollService;

@Controller
@RequestMapping("/hint")
public class HintController {

	@Autowired
	private PollService pollService;

	@Autowired
	private HintService hintService;

	private Integer toSave;

	@RequestMapping("/list")
	public ModelAndView list(@RequestParam Integer q) {
		ModelAndView res;

		res = new ModelAndView("hint/list");

		Poll poll = pollService.findOne(q);

		res.addObject("hint", poll.getHints());
		res.addObject("pollId", q);

		return res;
	}

	@RequestMapping("/create")
	public ModelAndView create(@RequestParam Integer q) {
		ModelAndView res;

		res = new ModelAndView("hint/create");

		res.addObject("hint", hintService.create());
		toSave=q;

		return res;
	}

	@RequestMapping(value="/save", method=RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Hint hint,BindingResult binding) {
		Integer to = toSave;
		if (binding.hasErrors()) {
			list(to);
		} else {
			try {
				hintService.save(hint,to);
				return list(to);
			}catch (Exception e) {
				return list(to);
			}
		}
		toSave=null;
		return list(to);

	}

	@RequestMapping("/score")
	public ModelAndView score(@RequestParam Integer q,@RequestParam Integer p) {
		ModelAndView res;

		res = new ModelAndView("hint/score");

		res.addObject("hint", q);
		res.addObject("pollId", p);
		toSave=p;

		return res;
	}

	@RequestMapping(value="/score", method=RequestMethod.POST)
	public ModelAndView scoreSave(@RequestParam Integer q, @RequestParam Integer t) {	
		Integer to=toSave;	
		try {
			hintService.score(q,t);
			return list(to);
		}catch (Exception e) {
			return list(to);
		}

	}
	
	@RequestMapping(value="/remove")
	public ModelAndView remove(@RequestParam Integer q, @RequestParam Integer p) {	
		try {
			hintService.remove(q,p);
			return list(p);
		}catch (Exception e) {
			return list(p);
		}

	}

}
