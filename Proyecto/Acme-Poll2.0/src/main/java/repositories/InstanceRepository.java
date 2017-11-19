
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Instance;

@Repository
public interface InstanceRepository extends JpaRepository<Instance, Integer> {

}
