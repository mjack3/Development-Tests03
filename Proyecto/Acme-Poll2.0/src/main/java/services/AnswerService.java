
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Answer;
import repositories.AnswerRepository;

@Service
@Transactional
public class AnswerService {

	//Manager repositories

	@Autowired
	private AnswerRepository answerRepository;


	//Constructor

	public AnswerService() {
		super();
	}

	//CRUD Methods
	
	public void delete(Integer arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(answerRepository.exists(arg0));
		answerRepository.delete(arg0);
	}

	public List<Answer> findAll() {
		return answerRepository.findAll();
	}

	public Answer findOne(Integer arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(answerRepository.exists(arg0));
		return answerRepository.findOne(arg0);
	}

	public Answer save(Answer arg0) {
		Assert.notNull(arg0);
		return answerRepository.save(arg0);
	}
	
	public Answer update(Answer arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(answerRepository.exists(arg0.getId()));
		return answerRepository.save(arg0);
	}

}
