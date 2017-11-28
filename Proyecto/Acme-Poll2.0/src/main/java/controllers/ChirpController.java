
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Actor;
import domain.Chirp;
import security.LoginService;
import services.ActorService;
import services.ChirpService;

@Controller
@RequestMapping("/chirp/actor")
public class ChirpController {

	@Autowired
	private ChirpService	chirpService;
	@Autowired
	private LoginService	loginService;
	@Autowired
	private ActorService	actorService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final Integer q) {
		ModelAndView result;
		try {
			result = new ModelAndView("chirp/list");
			result.addObject("requestURI", "chirp/actor/list.do");
			result.addObject("chirp", this.actorService.findOne(q).getChirps());
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/mylist", method = RequestMethod.GET)
	public ModelAndView listmy() {
		ModelAndView result;

		result = new ModelAndView("chirp/list");
		result.addObject("requestURI", "chirp/actor/mylist.do");
		final Actor actor = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
		result.addObject("chirp", actor.getChirps());

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		result = new ModelAndView("chirp/create");

		final Chirp chirp = this.chirpService.create();
		result.addObject("chirp", chirp);

		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Chirp chirp, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			for (final ObjectError e : binding.getAllErrors())
				System.out.println(e.toString());

			result = this.createNewModelAndView(chirp, null);
		} else
			try {

				this.chirpService.save(chirp);

				result = new ModelAndView("redirect:/chirp/actor/mylist.do");

			} catch (final Throwable th) {
				th.printStackTrace();
				result = this.createNewModelAndView(chirp, "actor.commit.error");
			}
		return result;
	}

	protected ModelAndView createNewModelAndView(final Chirp chirp, final String message) {
		ModelAndView result;

		result = new ModelAndView("chirp/create");
		result.addObject("chirp", chirp);
		result.addObject("message", message);
		return result;
	}

}
