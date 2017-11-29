
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Folder extends DomainEntity {

	private String name;


	public Folder() {
		super();
	}
	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}


	// Relationships ----------------------------------------------------------

	private Collection<MailMessage>	mailMessages;


	@OneToMany
	@NotNull
	public Collection<MailMessage> getMailMessages() {
		return this.mailMessages;
	}

	public void setMailMessages(final Collection<MailMessage> mailMessages) {
		this.mailMessages = mailMessages;
	}

}
