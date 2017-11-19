package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Chirp;
import repositories.ChirpRepository;

@Service
@Transactional
public class ChirpService {

	@Autowired
	private ChirpRepository chirpRepository;
	
	public ChirpService() {
		super();
	}

	public void delete(Integer arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(chirpRepository.exists(arg0));
		chirpRepository.delete(arg0);
	}

	public List<Chirp> findAll() {
		return chirpRepository.findAll();
	}

	public Chirp findOne(Integer arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(chirpRepository.exists(arg0));
		return chirpRepository.findOne(arg0);
	}

	public Chirp save(Chirp arg0) {
		Assert.notNull(arg0);
		return chirpRepository.save(arg0);
	}
	
	public Chirp update(Chirp arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(chirpRepository.exists(arg0.getId()));
		return chirpRepository.save(arg0);
	}
	
	
}
