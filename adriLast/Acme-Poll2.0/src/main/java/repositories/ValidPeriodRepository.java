package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.ValidPeriod;

@Repository
public interface ValidPeriodRepository extends JpaRepository<ValidPeriod, Integer>{

}
