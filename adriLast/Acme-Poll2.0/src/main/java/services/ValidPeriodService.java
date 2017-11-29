package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.ValidPeriod;
import repositories.ValidPeriodRepository;

@Service
@Transactional
public class ValidPeriodService {
	
	//Manager repositories

	@Autowired
	private ValidPeriodRepository validPeriodRepository;


	//Constructor

	public ValidPeriodService() {
		super();
	}

	//CRUD Methods
	public ValidPeriod get() {
		return validPeriodRepository.findAll().get(0);
	}
	
	public void delete(Integer arg0) {
		Assert.notNull(arg0);
		validPeriodRepository.delete(arg0);
	}


	public ValidPeriod findOne(Integer arg0) {
		Assert.notNull(arg0);
		return validPeriodRepository.findOne(arg0);
	}


	public ValidPeriod save(ValidPeriod arg0) {
		Assert.notNull(arg0);
		return validPeriodRepository.save(arg0);
	}
	
}
