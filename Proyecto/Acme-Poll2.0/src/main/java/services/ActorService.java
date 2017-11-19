
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Administrator;
import domain.Poller;
import repositories.ActorRepository;
import security.LoginService;

@Service
@Transactional
public class ActorService {

	//Manager repositories

	@Autowired
	private ActorRepository actorRepository;
	
	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private PollerService pollerService;


	//Constructor

	public ActorService() {
		super();
	}

	//CRUD Methods

	public void delete(Integer arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(actorRepository.exists(arg0));
		actorRepository.delete(arg0);
	}

	public List<Actor> findAll() {
		return actorRepository.findAll();
	}

	public Actor findOne(Integer arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(actorRepository.exists(arg0));
		return actorRepository.findOne(arg0);
	}

	public Actor save(Actor arg0) {
		Assert.notNull(arg0);
		return actorRepository.save(arg0);
	}
	
	public Actor update(Actor arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(actorRepository.exists(arg0.getId()));
		return actorRepository.save(arg0);
	}
	
	public Actor getActual(){
		Actor res=null;
		
		Administrator a= administratorService.findActorByUsername(LoginService.getPrincipal().getId());
		Poller p = pollerService.findActorByUsername(LoginService.getPrincipal().getId());
		
		if(a!=null) {
			res = a;
		}else {
			res=p;
		}
		
		return res;
	}
}
