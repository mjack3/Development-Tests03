
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.PollerRepository;
import domain.Poller;

@Service
@Transactional
public class PollerService {

	//Manager repositories

	@Autowired
	private PollerRepository pollerRepository;


	//Constructor

	public PollerService() {
		super();
	}

	//CRUD Methods

	public List<Poller> findAll() {
		return this.pollerRepository.findAll();
	}

	public Poller findOne(final Integer arg0) {
		Assert.notNull(arg0);
		return this.pollerRepository.findOne(arg0);
	}

	//CRUD Methods

	public Poller save(Poller poller) {
		Assert.notNull(poller);
		Poller p = null;

		if (this.exists(poller.getId())) {
			p = this.findOne(poller.getId());
			p.setName(poller.getName());
			p.setEmail(poller.getEmail());
			p.setPhone(poller.getPhone());
			p.setSurname(poller.getSurname());
			p.setAddress(poller.getAddress());
			p.setPolls(poller.getPolls());
			p = this.pollerRepository.save(p);
		} else {
			Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			poller.getUserAccount().setPassword(encoder.encodePassword(poller.getUserAccount().getPassword(), null));
			p = this.pollerRepository.save(poller);
		}
		return p;
	}

	public boolean exists(final Integer arg0) {
		Assert.notNull(arg0);
		return this.pollerRepository.exists(arg0);
	}

	public Poller findActorByUsername(final Integer id) {
		Assert.notNull(id);
		return this.pollerRepository.findOneUserAccount(id);
	}

	public Poller findPollerFromPoll(final int id) {
		Assert.notNull(id);
		return this.pollerRepository.findPollerFromPoll(id);
	}

	public List<Poller> pollersBanned() {
		// TODO Auto-generated method stub
		return this.pollerRepository.pollersBanned();
	}

	public Boolean isBanned(int pollerId) {
		Assert.isTrue(pollerId!=0);
		Poller poller = this.findOne(pollerId);
		return this.pollersBanned().contains(poller);
		
	}

}
