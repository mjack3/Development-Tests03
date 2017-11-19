package repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Bill;

public interface BillRepository extends JpaRepository<Bill, Integer>{

	@Query("select b from Bill b where b.paid = 1 and b.endorsed = 0")
	List<Bill> billsPaid();
	
	@Query("select b from Bill b where b.endorsed = 1 and b.paid = 1")
	List<Bill> billsEndorsed();
	
	@Query("select count(b) from Bill b where b.paid = 1 and b.endorsed = 0/(select count(c) from Bill c)*1.0")
	Double ratioBillsHaveToBeEndorsed();
	
	@Query("select count(b) from Bill b where b.endorsed = 1 and b.paid = 1/(select count(c) from Bill c)*1.0")
	Double ratioBillsHaveBeenEndorsed();
	
	@Query("select count(b) from Bill b where b.date < ?1 and b.paid=0/(select count(c) from Bill c)*1.0")
	Double ratioBillsOverdue(Date now);
	
	@Query("select avg(c.amount),max(c.amount),min(c.amount) from Bill c where c.paid=0")
	Object[] avgMaxMinAmountToBePaid();
}
