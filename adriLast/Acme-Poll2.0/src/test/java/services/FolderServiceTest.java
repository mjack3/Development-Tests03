package services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Folder;
import domain.MailMessage;
import services.FolderService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class FolderServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private FolderService	folderService;


	//Caso de uso positivo crear una carpeta
	@Test
	public void positiveTest0() {

		List<MailMessage> messages = new ArrayList<MailMessage>();
		template("admin", "folderName_2", messages, null);

	}

	//Caso de uso positivo crear las 4 carpetas principales
	@Test
	public void positiveTest1() {

		Assert.isTrue(folderService.createDefaultFolders().size() == 4);
	}

	//Caso de uso negativo cambiar el nombre a una carpeta principal

	@Test
	public void negativeTest0() {



		Folder folder = folderService.findOne(211);

		folder.setName("trashbox");

		try {
			folderService.saveCreate(folder);
		} catch (Exception e) {
			checkExceptions(IllegalArgumentException.class, e.getClass());
		}

	}
	//Caso de uso negativo crear un folder con alguien no autenticado
	@Test
	public void negativeTest1() {

		List<MailMessage> messages = new ArrayList<MailMessage>();
		template(null, "folder_3", messages, IllegalArgumentException.class);

	}
	//Caso de uso negativo crear un folder nullo
	@Test
	public void negativeTest2() {

		List<MailMessage> messages = new ArrayList<MailMessage>();
		template("dancer2", null, messages, IllegalArgumentException.class);

	}

	// Ancillary methods ------------------------------------------------------
	protected void template(final String username, final String folderName, final List<MailMessage> messages, final Class<?> expected) {
		Class<?> caught = null;

		try {
			authenticate(username);

			Folder folder = folderService.create();
			folder.setName(folderName);
			folder.setMailMessages(messages);

			folderService.saveCreate(folder);
			folderService.flush();

			unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);
	}
}
