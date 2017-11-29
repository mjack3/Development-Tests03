
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Access(AccessType.PROPERTY)
@Embeddable
public class Gender {

	public Gender() {
		super();
		this.gender = Gender.UNDEFINED;	//	by defect
	}


	// Values -----------------------------------------------------------------

	public static final String	FEMALE		= "FEMALE";
	public static final String	MALE		= "MALE";
	public static final String	UNDEFINED	= "UNDEFINED";

	// Attributes -------------------------------------------------------------

	private String				gender;


	@NotBlank
	@Pattern(regexp = "^" + Gender.FEMALE + "|" + Gender.MALE + "|" + Gender.UNDEFINED + "$")
	public String getGender() {
		return this.gender;
	}

	public void setGender(final String gender) {
		this.gender = gender;
	}

}
