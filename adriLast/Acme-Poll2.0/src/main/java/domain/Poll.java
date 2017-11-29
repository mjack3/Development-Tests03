
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Poll extends DomainEntity {

	public Poll() {
		super();
	}


	private String	title;
	private String	ticket;
	private String	description;
	private String	banner;
	private Date	startDate;
	private Date	endDate;


	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@Column(unique = true)
	@Pattern(regexp = "^[a-zA-Z]{2}-\\d{5}$")
	public String getTicket() {
		return this.ticket;
	}

	public void setTicket(final String ticket) {
		this.ticket = ticket;
	}

	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@URL
	@NotBlank
	public String getBanner() {
		return this.banner;
	}

	public void setBanner(final String banner) {
		this.banner = banner;
	}
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getStartDate() {
		return this.startDate;
	}


	public void setStartDate(final Date startDate) {
		this.startDate = startDate;

	}


	//	Relationships	--------------

	private Collection<Question>	questions;
	private Collection<Instance>	instances;
	private Poller					poller;
	private Collection<Hint>	    hints;
	private Bill					bill;


	@OneToOne(optional=true)
	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	@NotNull
	@OneToMany(mappedBy = "poll", cascade = CascadeType.ALL)
	public Collection<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(final Collection<Question> questions) {
		this.questions = questions;
	}

	@NotNull
	@OneToMany(mappedBy = "poll")
	public Collection<Instance> getInstances() {
		return this.instances;
	}

	public void setInstances(final Collection<Instance> instances) {
		this.instances = instances;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Poller getPoller() {
		return this.poller;
	}

	public void setPoller(final Poller poller) {
		this.poller = poller;
	}

	@NotNull
	@OneToMany
	public Collection<Hint> getHints() {
		return hints;
	}

	public void setHints(Collection<Hint> hints) {
		this.hints = hints;
	}
	
	

}
