
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Answer extends DomainEntity {

	private Integer question;
	private Integer selected;
	
	public Answer() {
		super();
	}

	@NotNull
	@Range(min=1)
	public Integer getQuestion() {
		return question;
	}

	public void setQuestion(Integer question) {
		this.question = question;
	}

	@NotNull
	@Range(min=0)
	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}

	

	
	

}