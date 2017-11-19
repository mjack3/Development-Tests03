
package controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Actor;
import domain.Answer;
import domain.Poll;
import services.ActorService;
import services.InstanceService;
import services.PollService;

@Controller
@RequestMapping("/answer")
public class AnswerController {

	@Autowired
	private PollService		pollService;

	@Autowired
	private InstanceService	instanceService;
	
	@Autowired
	private ActorService	actorService;

	private Integer			toSave;


	@RequestMapping("/answer")
	public ModelAndView answer(@RequestParam final Integer q) {
		ModelAndView res;

		res = new ModelAndView("answer/answer");

		final Poll poll = this.pollService.findOne(q);
		this.toSave = q;

		res.addObject("question", poll.getQuestions());

		return res;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(final String data, final String gender, final String city, final String name) {

		ModelAndView res;

		try {

			/*
			 * final Pattern pattern = Pattern.compile("^MALE|FEMALE|HOMBRE|MUJER|$");
			 * final Matcher m = pattern.matcher(gender);
			 * 
			 * if (!m.matches()) {
			 * res = this.answer(this.toSave);
			 * res.addObject("genderMessage", "must.be.gender");
			 * return res;
			 * 
			 * }
			 */
			
			final String[] answers = data.substring(1, data.length()).split(",");
			final List<Answer> ansToSave = new LinkedList<Answer>();
			final Poll p = this.pollService.findOne(this.toSave);

			for (int i = 0; i < answers.length; i++) {
				final Answer a = new Answer();
				a.setSelected(new Integer(answers[i]));
				a.setQuestion(i + 1);
				ansToSave.add(a);
			}

			this.instanceService.save(ansToSave, p, city, gender, name);

			res = new ModelAndView("poll/list");
			res.addObject("poll", this.pollService.findPollActivated());

		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/welcome/index.o");
			res.addObject("message", "answer.commit.error");

		}
		return res;
	}
}
