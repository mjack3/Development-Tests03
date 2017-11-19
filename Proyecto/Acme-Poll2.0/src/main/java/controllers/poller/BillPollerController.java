package controllers.poller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Bill;
import domain.Poller;
import security.LoginService;
import services.BillService;
import services.PollerService;

@Controller
@RequestMapping("/bill/poller")
public class BillPollerController {

	@Autowired
	private BillService billService;
	
	@Autowired
	private PollerService pollerService;
	
	
	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView res;

		res = new ModelAndView("bill/list");
		
		Poller poller = pollerService.findActorByUsername(LoginService.getPrincipal().getId());
		
		res.addObject("bill", poller.getBills());

		return res;
	}
	
	@RequestMapping("/add")
	public ModelAndView add(@RequestParam Integer q) {
		ModelAndView res;

		res = new ModelAndView("bill/add");
		
		Bill bill = billService.findOne(q);
		
		res.addObject("bill", bill);
		res.addObject("error",false);

		return res;
	}
	
	@RequestMapping(value="/confirmAdd",method = RequestMethod.POST)
	public ModelAndView confirmAdd(@Valid Bill bill,BindingResult binding) {
		ModelAndView res;
		if(bill.getReceipt().isEmpty()) {
			res = new ModelAndView("bill/add");			
			res.addObject("bill", bill);
			res.addObject("error",true);
			return res;
		}
		if (binding.hasErrors()) {
			res = new ModelAndView("bill/add");			
			res.addObject("bill", bill);
			res.addObject("error",true);
		}else {
			res = new ModelAndView("bill/confirmAdd");
			
			res.addObject("bill", bill);
		}
		return res;
	}
	
	@RequestMapping(value="/saveAdd",method = RequestMethod.POST)
	public ModelAndView saveAdd(@Valid Bill bill,BindingResult binding) {
		ModelAndView res;
		if(bill.getReceipt().isEmpty()) {
			res = new ModelAndView("bill/add");			
			res.addObject("bill", bill);
			res.addObject("error",true);
			return res;
		}
		if (binding.hasErrors()) {
			res = new ModelAndView("bill/add");			
			res.addObject("bill", bill);
			res.addObject("error",true);
		} else {
			try {
				billService.save(bill);
				return list();
			} catch (final Throwable th) {
				th.printStackTrace();
				res = new ModelAndView("bill/add");			
				res.addObject("bill", bill);
				res.addObject("error",true);
			}	
	}
		return res;
}
	
}
