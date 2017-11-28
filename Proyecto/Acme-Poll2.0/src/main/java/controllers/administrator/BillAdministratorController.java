
package controllers.administrator;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Bill;
import domain.Poll;
import services.BillService;
import services.PollService;
import services.ValidPeriodService;

@RequestMapping("/bill/administrator")
@Controller
public class BillAdministratorController {

	@Autowired
	private BillService			billService;

	@Autowired
	private PollService			pollService;

	@Autowired
	private ValidPeriodService	validPeriodService;

	private Integer				toSave;


	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;

		Date actualDate = Calendar.getInstance().getTime();
		result = new ModelAndView("poll/list");
		result.addObject("requestURI", "poll/list.do");
		final List<Poll> catalogue = pollService.findPollActivated();
		result.addObject("poll", catalogue);
		result.addObject("actualDate", actualDate);
		result.addObject("validPeriod", validPeriodService.get().getMinimumPeriod());

		return result;
	}

	@RequestMapping("/listPaid")
	public ModelAndView listPaid() {
		ModelAndView res;

		res = new ModelAndView("bill/list");

		res.addObject("bill", billService.billsPaid());
		res.addObject("listPaid", true);

		return res;
	}

	@RequestMapping("/listEndorsed")
	public ModelAndView listEndorsed() {
		ModelAndView res;

		res = new ModelAndView("bill/list");

		res.addObject("bill", billService.billsEndorsed());
		res.addObject("listPaid", false);

		return res;
	}

	@RequestMapping("/endorse")
	public ModelAndView endorse(@RequestParam Integer q) {
		Bill bill = billService.findOne(q);

		try {
			billService.endorse(bill);
			return listEndorsed();

		} catch (Exception e) {
			return listEndorsed();
		}
	}

	@RequestMapping("/create")
	public ModelAndView create(@RequestParam Integer q) {
		ModelAndView res;

		try {
			res = new ModelAndView("bill/create");

			toSave = q;

			res.addObject("bill", billService.create());
		} catch (Throwable e) {
			res = new ModelAndView("redirect:/welcome/index.do");
		}

		return res;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(Bill bill) {

		try {
			billService.save(bill, toSave);
			return list();
		} catch (Exception e) {
			e.printStackTrace();
			return list();
		}

	}

}
