
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Gender;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Integer> {

}
