
package controllers.poller;

import java.util.Date;

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
import services.QuestionService;
import domain.Poll;
import domain.Poller;
import domain.Question;
import form.QuestionForm;

@Controller
@RequestMapping("/question/poller")
public class QuestionPollerController {

	@Autowired
	private PollService		pollService;

	@Autowired
	private QuestionService	questionService;

	@Autowired
	private PollerService	pollerService;


	@RequestMapping("/list")
	public ModelAndView list(@RequestParam final Integer q) {
		ModelAndView res;

		res = new ModelAndView("question/list");
		try {
			final Poll poll = this.pollService.findOne(q);

			//	comprobacion para editar las questions

			if (LoginService.hasRole("POLLER")) {
				final Poller poller = this.pollerService.findActorByUsername(LoginService.getPrincipal().getId());
				if (poller.getPolls().contains(poll) && poll.getStartDate().after(new Date()))
					res.addObject("editable", 1);

			}

			// *********************************

			res.addObject("question", poll.getQuestions());
			res.addObject("pollId", poll.getId());
		} catch (final Throwable e) {
			res = new ModelAndView("redirect:/welcome/index.do");
		}

		return res;
	}

	//	CREAR PREGUNTAS	**************

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final Integer pollId) {
		ModelAndView resul;

		try {

			final Poll poll = this.pollService.findOne(pollId);

			final QuestionForm questionForm = new QuestionForm();
			questionForm.setPoll(poll);

			if (poll.getQuestions() != null)
				questionForm.setNumber(poll.getQuestions().size() + 1);
			else
				questionForm.setNumber(1);

			resul = this.createEditModelAndView2(questionForm, null);

		} catch (final Throwable oops) {
			resul = new ModelAndView("redirect:/welcome/index.do");
		}

		return resul;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView createSave(final QuestionForm questionForm, final BindingResult bindingResult) {

		ModelAndView resul;

		try {

			final Question question = this.questionService.reconstruct(questionForm, bindingResult);

			if (bindingResult.hasErrors())
				resul = this.createEditModelAndView2(questionForm, null);
			else {

				if (question.getChoices().size() < 2) {
					bindingResult.rejectValue("choices", "choices.error", "invalid");
					throw new IllegalArgumentException();
				}

				this.questionService.save(question);
				resul = new ModelAndView("redirect:/welcome/index.do");
			}

		} catch (final Throwable oops) {
			resul = this.createEditModelAndView2(questionForm, "acme.commit.error");
		}

		return resul;
	}

	private ModelAndView createEditModelAndView2(final QuestionForm questionForm, final String message) {
		// TODO Auto-generated method stub

		ModelAndView resul;

		resul = new ModelAndView("question/create");
		resul.addObject("questionForm", questionForm);

		resul.addObject("message", message);
		return resul;
	}

	//	EDITAR PREGUNTAS	**********

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final Integer questionId) {

		ModelAndView resul;

		try {

			resul = new ModelAndView("question/edit");
			final Question question = this.questionService.findOne(questionId);

			resul.addObject("question", question);

		} catch (final Throwable oops) {
			resul = new ModelAndView("redirect:/welcome/index.do");
		}

		return resul;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(Question question, final BindingResult bindingResult) {

		ModelAndView resul;

		try {

			if (question.getChoices().size() < 2) {
				bindingResult.rejectValue("choices", "choices.error", "invalid");
				throw new IllegalArgumentException();
			}

			question = this.questionService.reconstruct(question, bindingResult);

			if (bindingResult.hasErrors())
				resul = this.createEditModelAndView(question, null);
			else {

				this.questionService.save(question);
				resul = new ModelAndView("redirect:/welcome/index.do");
			}

		} catch (final Throwable oops) {
			resul = this.createEditModelAndView(question, "acme.commit.error");
		}

		return resul;

	}

	//	ELIMINAR PREGUNTAS

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Question question, final BindingResult bindingResult) {
		ModelAndView resul;

		try {

			Assert.isTrue(LoginService.hasRole("POLLER"));
			this.questionService.delete(question.getId());
			resul = new ModelAndView("redirect:/welcome/index.do");

		} catch (final Throwable oops) {
			resul = this.createEditModelAndView(question, "acme.commit.error");
		}

		return resul;
	}

	private ModelAndView createEditModelAndView(final Question question, final String message) {
		// TODO Auto-generated method stub

		final ModelAndView resul = new ModelAndView("question/edit");

		resul.addObject("question", question);
		resul.addObject("message", message);

		return resul;
	}

	@RequestMapping("/listChoice")
	public ModelAndView listChoice(@RequestParam final Integer q) {
		ModelAndView res;

		res = new ModelAndView("choice/list");
		try {
			final Question question = this.questionService.findOne(q);

			res.addObject("choice", question.getChoices());
		} catch (final Throwable e) {
			res = new ModelAndView("redirect:/welcome/index.do");
		}

		return res;
	}
}
