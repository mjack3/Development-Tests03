package services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Hint;
import domain.Poll;
import repositories.HintRepository;

@Service
@Transactional
public class HintService {

	@Autowired
	private HintRepository hintRepository;
	
	@Autowired
	private PollService 	pollService;
	
	public HintService() {
		super();
	}

	public void delete(Integer arg0) {
		Assert.notNull(arg0);

		hintRepository.delete(arg0);
	}

	public List<Hint> findAll() {
		return hintRepository.findAll();
	}

	public Hint findOne(Integer arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(hintRepository.exists(arg0));
		return hintRepository.findOne(arg0);
	}

	public Hint save(Hint arg0, Integer q) {
		Assert.notNull(arg0);
		
		Poll p = pollService.findOne(q);
		
		List<Hint> list = (List<Hint>)p.getHints();
		arg0=hintRepository.save(arg0);
		list.add(arg0);
		
		p.setHints(list);
		
		pollService.update(p);
		
		return arg0;
	}
	
	public Hint update(Hint arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(hintRepository.exists(arg0.getId()));
		return hintRepository.save(arg0);
	}

	public Hint create() {
		Hint hint = new Hint();
		
		hint.setMark(new Double(0.0));
		hint.setMarks(new ArrayList<Integer>());
		hint.setText("");
		
		return hint;
	}

	public Hint score(Integer q, Integer t) {
		Hint h = hintRepository.findOne(q);
		
		List<Integer> list = h.getMarks();
		list.add(t);
		h.setMarks(list);
		
		Double mark = 0.0;
		Integer ac = 0;
		for(Integer i: h.getMarks()) {
			ac +=i;
		}
		mark = (double) (ac/h.getMarks().size());
		h.setMark(mark);
		
		return hintRepository.save(h);
		
	}

	public void remove(Integer q,Integer p) {
		Hint h = hintRepository.findOne(q);
		Poll poll = pollService.findOne(p);
		
		List<Hint> list = (List<Hint>)poll.getHints();
		list.remove(h);
		poll.setHints(list);
		
		pollService.update(poll);
		
		hintRepository.delete(h);
		
	}
}
