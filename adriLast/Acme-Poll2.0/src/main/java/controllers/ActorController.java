
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ChirpService;

@Controller
@RequestMapping("/actor")
public class ActorController {

	@Autowired
	private ActorService	actorService;
	@Autowired
	private ChirpService	chirpService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listGeneral() {
		ModelAndView result;
		try {
			result = new ModelAndView("actor/list");

			result.addObject("actor", this.chirpService.actorChirps());
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

}
