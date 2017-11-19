package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Hint;

@Repository
public interface HintRepository extends JpaRepository<Hint, Integer> {

}
