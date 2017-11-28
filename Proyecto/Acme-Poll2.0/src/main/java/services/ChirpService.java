
package services;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Administrator;
import domain.Chirp;
import domain.Poller;
import repositories.ChirpRepository;
import security.Authority;
import security.LoginService;

@Service
@Transactional
public class ChirpService {

	@Autowired
	private ChirpRepository			chirpRepository;

	//Services
	@Autowired
	private PollerService			pollerService;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private LoginService			loginService;

	@Autowired
	private ActorService			actorService;


	//Constructor
	public ChirpService() {
		super();
	}

	//CRUD Methods

	public Chirp create() {
		final Chirp chirp = new Chirp();

		chirp.setText(new String());

		chirp.setMomentWritten(new Date());

		return chirp;
	}
	public void delete(final Chirp chirp) {
		Assert.notNull(chirp);

		this.chirpRepository.delete(chirp);
	}

	public List<Chirp> findAll() {
		return this.chirpRepository.findAll();
	}

	public Chirp findOne(final int id) {
		Assert.isTrue(LoginService.isAnyAuthenticated());
		Assert.isTrue(this.chirpRepository.exists(id));
		return this.chirpRepository.findOne(id);
	}

	public Chirp save(Chirp chirp) {
		Assert.notNull(chirp);

		final Actor a = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());

		chirp = this.chirpRepository.save(chirp);

		final List<Chirp> chirpsActor = a.getChirps();
		chirpsActor.add(chirp);
		a.setChirps(chirpsActor);

		if (a.getUserAccount().getAuthorities().contains(Authority.POLLER))
			this.pollerService.save((Poller) a);

		else if (a.getUserAccount().getAuthorities().contains(Authority.ADMINISTRATOR))
			this.administratorService.save((Administrator) a);

		return this.chirpRepository.save(chirp);
	}

	public List<Actor> actorChirps() {
		return this.chirpRepository.actorChirps();
	}

	public Collection<Chirp> findAllPrincipal() {
		// TODO Auto-generated method stub
		Assert.isTrue(LoginService.isAnyAuthenticated());
		final Actor a = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());

		return a.getChirps();

	}

	//Other Methods

}
