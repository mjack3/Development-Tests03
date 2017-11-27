
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Instance;

@Repository
public interface InstanceRepository extends JpaRepository<Instance, Integer> {

	@Query("select c from Instance c where c.ticker=?1")
	Instance findByTicker(String ticker);
	
	@Query("select avg(c.edits) from Instance c ")
	Double avgEditPerInstance();
}
