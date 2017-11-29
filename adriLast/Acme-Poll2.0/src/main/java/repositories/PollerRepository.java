
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Poller;

@Repository
public interface PollerRepository extends JpaRepository<Poller, Integer> {

	@Query("select p from Poller p where p.userAccount.id = ?1")
	Poller findOneUserAccount(int id);

	@Query("select p.poller from Poll p where p.id = ?1")
	Poller findPollerFromPoll(int id);
	
	@Query("select u from Poller u where u.userAccount.banned = true")
	List<Poller> pollersBanned();

}
