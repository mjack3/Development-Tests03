
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class MailMessage extends DomainEntity {

	private String		subject;
	private String		body;
	private Priority	priority;
	private Date		sentMoment;


	public MailMessage() {
		super();
	}

	@NotBlank
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	@NotBlank
	public String getBody() {
		return this.body;
	}

	public void setBody(final String body) {
		this.body = body;
	}
	
	@Valid
	@NotNull
	public Priority getPriority() {
		return this.priority;
	}

	public void setPriority(final Priority priority) {
		this.priority = priority;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getSentMoment() {
		return this.sentMoment;
	}

	public void setSentMoment(final Date sentMoment) {
		this.sentMoment = sentMoment;
	}


	// Relationships ----------------------------------------------------------

	private Actor	sender;
	private Actor	recipient;


	@ManyToOne(optional = false)
	@NotNull
	public Actor getSender() {
		return this.sender;
	}

	@ManyToOne(optional = false)
	@NotNull
	public Actor getRecipient() {
		return this.recipient;
	}

	public void setSender(final Actor sender) {
		this.sender = sender;
	}

	public void setRecipient(final Actor recipient) {
		this.recipient = recipient;
	}

}
