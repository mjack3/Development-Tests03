
package services;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Answer;
import domain.Instance;
import domain.Poll;
import repositories.InstanceRepository;

@Service
@Transactional
public class InstanceService {

	//Manager repositories

	@Autowired
	private InstanceRepository	instanceRepository;

	@Autowired
	private PollService			pollService;

	@Autowired
	private AnswerService		answerService;


	//Constructor

	public InstanceService() {
		super();
	}

	//CRUD Methods

	public void delete(final Integer arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(this.instanceRepository.exists(arg0));
		this.instanceRepository.delete(arg0);
	}

	public List<Instance> findAll() {
		return this.instanceRepository.findAll();
	}

	public Instance findOne(final Integer arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(this.instanceRepository.exists(arg0));
		return this.instanceRepository.findOne(arg0);
	}

	public Instance save(final Instance arg0) {
		Assert.notNull(arg0);
		return this.instanceRepository.save(arg0);
	}

	public Instance update(final Instance arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(this.instanceRepository.exists(arg0.getId()));
		arg0.setEdits(arg0.getEdits()+1);
		return this.instanceRepository.save(arg0);
	}

	public Instance save( List<Answer> ansToSave,  Poll p,  String city,  String gender,  String name) {

		Collection<Instance> instances = p.getInstances();

		for (final Instance instance : instances)
			if (instance.getName().equalsIgnoreCase(name))
				return null;

		Instance ins = new Instance();
		ins.setGender(city);
		ins.setCity(gender);
		ins.setPoll(p);

		List<Answer> ansFinal = new LinkedList<Answer>();
		for (Answer a : ansToSave) {
			a = this.answerService.save(a);
			ansFinal.add(a);
		}

		ins.setAnswers(ansFinal);
		ins.setName(name);

		List<Instance> insts = (List<Instance>) p.getInstances();
		insts.add(ins);
		p.setInstances(insts);
		
		ins.setTicker(generateTicker());
		ins.setEdits(0);

		return this.instanceRepository.save(ins);
	}

	
	public Instance findByTicker(String ticker) {
		return instanceRepository.findByTicker(ticker);
	}
	
	private String generateTicker() {
		String letters = "QWERTYUIOPÑLKJHGFDSAZXCVBNM";
		String numbers = "1234567890";
		
		Random rnLetters = new Random();
		Random rnNumbers = new Random();
		
		String res="";
		for(int i=0;i<2;i++) {
			int index=rnLetters.nextInt(letters.length());
			res= res + letters.substring(index, index+1);
		}
		
		res = res + "-";
		
		for(int i=0;i<5;i++) {
			int index=rnNumbers.nextInt(numbers.length());
			res= res + numbers.substring(index, index+1);
		}
		
		return res;
	}

	public Double avgEditPerInstance() {
		return instanceRepository.avgEditPerInstance();
	}

	


}
