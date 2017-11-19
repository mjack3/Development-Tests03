package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class ValidPeriod extends DomainEntity{
	
	private Integer minimumPeriod;
	
	public ValidPeriod() {
		super();
	}

	@NotNull
	@Range(min=1)
	public Integer getMinimumPeriod() {
		return minimumPeriod;
	}

	public void setMinimumPeriod(Integer minimumPeriod) {
		this.minimumPeriod = minimumPeriod;
	}
	
	

}
