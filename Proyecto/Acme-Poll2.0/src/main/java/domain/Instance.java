
package domain;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Instance extends DomainEntity {

	public Instance() {
		super();
		this.answers = new HashSet<>();
	}


	private String	ticker;
	private String	name;
	private String	gender;
	private String	city;
	private Integer	edits;


	@NotBlank
	@Column(unique = true)
	public String getTicker() {
		return this.ticker;
	}
	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(final String gender) {
		this.gender = gender;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	@NotNull
	@Range(min = 0)
	public Integer getEdits() {
		return this.edits;
	}
	public void setEdits(final Integer edits) {
		this.edits = edits;
	}


	//	Relathipnships	-----------
	private Poll				poll;
	private Collection<Answer>	answers;


	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Poll getPoll() {
		return this.poll;
	}

	public void setPoll(final Poll poll) {
		this.poll = poll;
	}

	@NotNull
	@OneToMany()
	public Collection<Answer> getAnswers() {
		return this.answers;
	}

	public void setAnswers(final Collection<Answer> answers) {
		this.answers = answers;
	}

}
