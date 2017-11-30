
package services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.QuestionRepository;
import domain.Question;
import form.QuestionForm;

@Service
@Transactional
public class QuestionService {

	//Manager repositories

	@Autowired
	private QuestionRepository	questionRepository;


	//Constructor

	public QuestionService() {
		super();
	}

	//CRUD Methods

	public void delete(final Integer arg0) {
		Assert.notNull(arg0);

		this.questionRepository.delete(arg0);
	}

	public List<Question> findAll() {
		return this.questionRepository.findAll();
	}

	public Question findOne(final Integer arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(this.questionRepository.exists(arg0));
		return this.questionRepository.findOne(arg0);
	}

	public Question save(final Question arg0) {
		Assert.notNull(arg0);
		return this.questionRepository.save(arg0);
	}

	public Question update(final Question arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(this.questionRepository.exists(arg0.getId()));
		return this.questionRepository.save(arg0);
	}


	@Autowired
	Validator	validator;


	public Question reconstruct(final Question question, final BindingResult bindingResult) {
		// TODO Auto-generated method stub

		final Question resul = this.findOne(question.getId());

		resul.setStatment(question.getStatment());
		resul.setChoices(question.getChoices());

		this.validator.validate(resul, bindingResult);

		return resul;
	}

	public Question reconstruct(final QuestionForm questionForm, final BindingResult bindingResult) {
		// TODO Auto-generated method stub
		final Question resul = new Question();
		final List<String> strings = new ArrayList<>();
		strings.addAll(questionForm.getChoices());
		resul.setChoices(strings);
		resul.setNumber(questionForm.getNumber());

		resul.setStatment(questionForm.getStatment());
		resul.setPoll(questionForm.getPoll());

		this.validator.validate(questionForm, bindingResult);
		return resul;
	}
}
