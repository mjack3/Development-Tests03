package services;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Bill;
import domain.Poll;
import repositories.BillRepository;
import repositories.PollRepository;

@Service
@Transactional
public class BillService {

	//Manager repositories

	@Autowired
	private BillRepository billRepository;

	@Autowired
	private PollService pollService;

	//Constructor

	public BillService() {
		super();
	}

	//CRUD Methods
	
	public Bill findOne(Integer arg0) {
		Assert.notNull(arg0);
		return billRepository.findOne(arg0);
	}


	public Bill save(Bill arg0) {
		Assert.notNull(arg0);
		arg0.setPaid(true);
		return billRepository.save(arg0);
	}
	
	
	//Other Methods
	
	public List<Bill> billsPaid() {
		return billRepository.billsPaid();
	}


	public List<Bill> billsEndorsed() {
		return billRepository.billsEndorsed();
	}

	public void endorse(Bill bill) {
		
		if(bill.getPaid()) {
			bill.setEndorsed(true);
		}
		
		billRepository.save(bill);
	}

	public Double ratioBillsHaveToBeEndorsed() {
		return billRepository.ratioBillsHaveToBeEndorsed();
	}

	public Double ratioBillsHaveBeenEndorsed() {
		return billRepository.ratioBillsHaveBeenEndorsed();
	}

	public Double ratioBillsOverdue() {
		return billRepository.ratioBillsOverdue(Calendar.getInstance().getTime());
	}

	public String avgMaxMinAmountToBePaid() {
		Object[] obj = billRepository.avgMaxMinAmountToBePaid();
		String res = "avg: " + obj[0].toString() + ", max: " + obj[1].toString() + 
				", min: " + obj[2].toString();
		return res;
	}

	public Bill create() {
		Bill b= new Bill();
		
		b.setAmount(new Double(0.0));
		b.setDate(Calendar.getInstance().getTime());
		b.setEndorsed(false);
		b.setPaid(false);
		b.setPoll(new Poll());
		b.setReceipt("");
		
		return b;
	}

	public Bill save(Bill bill, Integer pollId) {
		Poll poll = pollService.findOne(pollId);
		bill.setPoll(poll);
		bill = billRepository.save(bill);
		poll.setBill(bill);
		pollService.update(poll);
		
		return bill;
	}
	
	

	
}
