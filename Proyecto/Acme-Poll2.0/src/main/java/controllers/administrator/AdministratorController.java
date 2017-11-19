
package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Administrator;
import services.AdministratorService;
import services.BillService;
import services.PollService;

@RequestMapping("/administrator")
@Controller
public class AdministratorController {

	@Autowired
	private AdministratorService administratorService;
	@Autowired
	private PollService pollService;
	@Autowired
	private BillService billService;


	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(final int userAccountID) {
		ModelAndView result;
		Administrator admin;

		admin = this.administratorService.findActorByUsername(userAccountID);

		result = this.createEditModelAndView(admin);

		return result;
	}

	@RequestMapping(value = "/save-administrator", method = RequestMethod.POST, params = "save")
	public ModelAndView saveAdministrator(@Valid final Administrator admin, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			for (final ObjectError e : binding.getAllErrors())
				System.out.println(e.toString());
			result = this.createEditModelAndView(admin, "admin.commit.error");
		} else
			try {
				this.administratorService.save(admin);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (final Throwable th) {
				th.printStackTrace();
				result = this.createEditModelAndView(admin, "admin.commit.error");
			}
		return result;
	}
	
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		result = new ModelAndView("administrator/dashboard");
		
		result.addObject("findMinAvgStdMaxPollsByPoller",pollService.findMinAvgStdMaxPollsByPoller()
				);
		result.addObject("findMinAvgStdMaxInstancesByPoll",
				pollService.findMinAvgStdMaxInstancesByPoll());
		
		result.addObject("findMinAvgStdMaxQuestionByPoll", pollService.findMinAvgStdMaxQuestionByPoll());
		
		result.addObject("avgMaxMinAmountToBePaid", billService.avgMaxMinAmountToBePaid());
		
		result.addObject("ratioBillsHaveBeenEndorsed", billService.ratioBillsHaveBeenEndorsed().toString());
		
		result.addObject("ratioBillsHaveToBeEndorsed", billService.ratioBillsHaveToBeEndorsed().toString());
		
		result.addObject("ratioBillsOverdue", billService.ratioBillsOverdue().toString());
		
		return result;

	}

	protected ModelAndView createEditModelAndView(final Administrator admin) {

		ModelAndView result;
		result = this.createEditModelAndView(admin, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Administrator admin, final String message) {

		ModelAndView result;

		result = new ModelAndView("administrator/edit");
		result.addObject("administrator", admin);
		result.addObject("message", message);

		System.out.println(message);

		return result;
	}
	
	

}
