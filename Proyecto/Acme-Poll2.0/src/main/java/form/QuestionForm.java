
package form;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.ElementCollection;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import domain.Poll;

public class QuestionForm {

	private Poll				poll;
	private String				statment;
	private Integer				number;

	private Collection<String>	choices;


	public QuestionForm() {
		this.choices = new ArrayList<String>();
	}

	@NotBlank
	public String getStatment() {
		return this.statment;
	}
	public void setStatment(final String statment) {
		this.statment = statment;
	}
	public Poll getPoll() {
		return this.poll;
	}
	public void setPoll(final Poll poll) {
		this.poll = poll;
	}
	public Integer getNumber() {
		return this.number;
	}
	public void setNumber(final Integer number) {
		this.number = number;
	}

	@ElementCollection
	@NotNull
	@NotEmpty
	public Collection<String> getChoices() {
		return this.choices;
	}
	public void setChoices(final Collection<String> choices) {
		this.choices = choices;
	}

}
