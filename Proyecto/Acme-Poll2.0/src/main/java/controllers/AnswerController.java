
package controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.InstanceService;
import services.PollService;
import domain.Answer;
import domain.Instance;
import domain.Poll;

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
	private String			ticket;


	@RequestMapping("/answer")
	public ModelAndView answer(@RequestParam final Integer q) {
		ModelAndView res;
		try {
			res = new ModelAndView("answer/answer");

			final Poll poll = this.pollService.findOne(q);
			this.toSave = q;

			res.addObject("question", poll.getQuestions());
		} catch (final Throwable e) {
			res = new ModelAndView("redirect:/welcome/index.do");
		}

		return res;
	}

	@RequestMapping("/correctSave")
	public ModelAndView correctSave() {
		ModelAndView res;

		res = new ModelAndView("answer/correctSave");

		res.addObject("ticket", this.ticket);

		return res;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(final String data, final String gender, final String city, final String name) {

		String res = null;
		final Poll p = this.pollService.findOne(this.toSave);

		try {

			final String[] answers = data.substring(1, data.length()).split(",");
			final List<Answer> ansToSave = new LinkedList<Answer>();

			for (int i = 0; i < answers.length; i++) {
				final Answer a = new Answer();
				a.setSelected(new Integer(answers[i]));
				a.setQuestion(i + 1);
				ansToSave.add(a);
			}

			//Se usa para diferenciar en la respuesta si ha respondido anteriormente esa encuesta
			final Instance resultado = this.instanceService.save(ansToSave, p, city, gender, name);
			if (resultado != null) {
				res = "poll/list";
				this.ticket = resultado.getTicker();
			} else
				res = "poller/list";

		} catch (final Exception e) {
			e.printStackTrace();
			res = "poll/list";
		}
		return res;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(final String data, final String gender, final String city, final String name, final String instance) {

		String res = null;
		final Poll p = this.pollService.findOne(this.toSave);

		try {

			final String[] answers = data.substring(1, data.length()).split(",");
			final List<Answer> ansToSave = new LinkedList<Answer>();
			final Instance ins = this.instanceService.findOne(new Integer(instance));

			for (int i = 0; i < answers.length; i++) {
				final Answer a = new Answer();
				a.setSelected(new Integer(answers[i]));
				a.setQuestion(i + 1);
				ansToSave.add(a);
			}

			//Se usa para diferenciar en la respuesta si ha respondido anteriormente esa encuesta
			final Object resultado = this.instanceService.update(ins, ansToSave, p, city, gender, name);
			if (resultado != null)
				res = "poll/list";
			else
				res = "poller/list";

			this.ticket = ((Instance) resultado).getTicker();
		} catch (final Exception e) {
			e.printStackTrace();
			res = "poll/list";
		}
		return res;
	}
}
