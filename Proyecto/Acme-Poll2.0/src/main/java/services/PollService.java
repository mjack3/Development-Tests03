
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.PollRepository;
import domain.Answer;
import domain.Hint;
import domain.Instance;
import domain.Poll;
import domain.Poller;
import domain.Question;

@Service
@Transactional
public class PollService {

	//Manager repositories

	@Autowired
	private PollRepository	pollRepository;

	@Autowired
	private InstanceService	instanceService;

	@Autowired
	private QuestionService	questionService;

	@Autowired
	private HintService		hintService;

	@Autowired
	private PollerService	pollerService;


	//Constructor

	public PollService() {
		super();
	}

	//CRUD Methods

	public List<Poll> findAll() {
		return this.pollRepository.findAll();
	}

	public Poll findOne(final Integer arg0) {
		Assert.notNull(arg0);
		return this.pollRepository.findOne(arg0);
	}

	public List<Poll> findPollActivated() {
		final Date now = Calendar.getInstance().getTime();

		//DateFormat.getDateInstance().format(now);

		//System.out.print(now.toString());

		return this.pollRepository.findPollActivated(now);

	}
	public String findMinAvgStdMaxPollsByPoller() {
		final String res = "Min: " + this.pollRepository.findMinAvgStdMaxPollsByPoller()[0] + ", Avg: " + this.pollRepository.findMinAvgStdMaxPollsByPoller()[1] + ", Std: " + this.pollRepository.findMinAvgStdMaxPollsByPoller()[2] + ", Max: "
			+ this.pollRepository.findMinAvgStdMaxPollsByPoller()[3];
		return res;

	}
	public String findMinAvgStdMaxInstancesByPoll() {
		final String res = "Min: " + this.pollRepository.findMinAvgStdMaxInstancesByPoll()[0] + ", Avg: " + this.pollRepository.findMinAvgStdMaxInstancesByPoll()[1] + ", Std: " + this.pollRepository.findMinAvgStdMaxInstancesByPoll()[2] + ", Max: "
			+ this.pollRepository.findMinAvgStdMaxInstancesByPoll()[3];
		return res;
	}

	public void delete(final Poll arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(this.pollRepository.exists(arg0.getId()));

		for (final Instance instance : arg0.getInstances())
			this.instanceService.delete(instance.getId());

		arg0.setInstances(new ArrayList<Instance>());

		for (final Question question : arg0.getQuestions())
			this.questionService.delete(question.getId());

		arg0.setQuestions(new ArrayList<Question>());

		for (final Hint hint : arg0.getHints())
			this.hintService.delete(hint.getId());

		arg0.setHints(new ArrayList<Hint>());

		final Poller poller = arg0.getPoller();

		final List<Poll> polls = (List<Poll>) poller.getPolls();
		polls.remove(arg0);
		poller.setPolls(polls);

		this.pollerService.save(poller);

		arg0.setPoller(new Poller());

		this.pollRepository.delete(arg0);
	}

	public Poll update(final Poll arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(this.pollRepository.exists(arg0.getId()));
		return this.pollRepository.save(arg0);
	}

	public String findMinAvgStdMaxQuestionByPoll() {
		final String res = "Min: " + this.pollRepository.findMinAvgStdMaxQuestionByPoll()[0] + ", Avg: " + this.pollRepository.findMinAvgStdMaxQuestionByPoll()[1] + ", Std: " + this.pollRepository.findMinAvgStdMaxQuestionByPoll()[2] + ", Max: "
			+ this.pollRepository.findMinAvgStdMaxQuestionByPoll()[3];
		return res;
	}

	public List<Integer> getResults(final Integer q) {

		final Poll poll = this.pollRepository.findOne(q);
		final Map<Integer, List<Integer>> res = new HashMap<Integer, List<Integer>>();
		final List<Question> questionsTotal = (List<Question>) poll.getQuestions();
		for (int i = 0; i < questionsTotal.size(); i++) {
			final Integer quest = i;
			final Integer cont = questionsTotal.get(i).getChoices().size();
			for (int b = 0; b < cont; b++)
				if (res.get(quest) == null || res.get(quest).isEmpty()) {
					final List<Integer> choicess = new LinkedList<Integer>();
					choicess.add(b, new Integer(0));
					res.put(quest, choicess);
				} else {
					final List<Integer> choices = res.get(quest);
					choices.add(b, new Integer(0));
					res.put(quest, choices);
				}
		}

		for (final Instance instance : poll.getInstances())
			for (final Answer answer : instance.getAnswers()) {
				final Integer question = answer.getQuestion() - 1;
				final Integer choice = answer.getSelected();
				final List<Integer> aux = res.get(question);
				Integer valAux = aux.get(choice);
				valAux++;
				aux.set(choice, valAux);
				res.put(question, aux);
			}

		final List<Integer> resfinal = new LinkedList<Integer>();

		for (int c = 0; c < questionsTotal.size(); c++)
			resfinal.addAll(res.get(new Integer(c)));

		return resfinal;
	}

}
