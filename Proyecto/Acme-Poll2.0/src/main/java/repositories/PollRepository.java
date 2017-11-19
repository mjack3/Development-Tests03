
package repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Poll;

@Repository
public interface PollRepository extends JpaRepository<Poll, Integer> {

	@Query("select p from Poll p where p.startDate <= ?1 and p.endDate >= ?1")
	List<Poll> findPollActivated(Date today);

	@Query("select min(p.polls.size),avg(p.polls.size), stddev(p.polls.size),max(p.polls.size) from Poller p")
	Double[] findMinAvgStdMaxPollsByPoller();

	@Query("select min(p.instances.size),avg(p.instances.size), stddev(p.instances.size),max(p.instances.size) from Poll p")
	Double[] findMinAvgStdMaxInstancesByPoll();

	//The minimum, the average, the standard deviation, and the maximum num-ber of questions per poll.
	@Query("select min(p.questions.size),avg(p.questions.size), stddev(p.questions.size),max(p.questions.size) from Poll p")
	Double[] findMinAvgStdMaxQuestionByPoll();
}
