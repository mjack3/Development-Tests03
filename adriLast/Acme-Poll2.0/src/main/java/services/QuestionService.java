
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Question;
import repositories.QuestionRepository;

@Service
@Transactional
public class QuestionService {

	//Manager repositories

	@Autowired
	private QuestionRepository questionRepository;



	//Constructor

	public QuestionService() {
		super();
	}

	//CRUD Methods

	public void delete(Integer arg0) {
		Assert.notNull(arg0);
		
		questionRepository.delete(arg0);
	}

	public List<Question> findAll() {
		return questionRepository.findAll();
	}

	public Question findOne(Integer arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(questionRepository.exists(arg0));
		return questionRepository.findOne(arg0);
	}

	public Question save(Question arg0) {
		Assert.notNull(arg0);
		return questionRepository.save(arg0);
	}
	
	public Question update(Question arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(questionRepository.exists(arg0.getId()));
		return questionRepository.save(arg0);
	}
}
