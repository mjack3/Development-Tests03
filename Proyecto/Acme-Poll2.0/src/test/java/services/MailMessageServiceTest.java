package services;

import java.util.Date;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Actor;
import domain.MailMessage;
import domain.Poller;
import domain.Priority;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
		"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class MailMessageServiceTest extends AbstractTest{
	
	// System under test ------------------------------------------------------
		@Autowired
		private MailMessageService	mailmessageService;
		@Autowired
		private PollerService	pollerService;


		//Caso de uso positivo crear un mensaje
		@Test
		public void positiveTest0() {

			Priority priority = new Priority();
			priority.setValue("NEUTRAL");

			Poller poller = pollerService.findAll().iterator().next();


			template("poller2", new Date(), "subject", "body", priority, poller, poller, null);

		}

		@Test
		public void positiveTest1() {

			Priority priority = new Priority();
			priority.setValue("LOW");

			Poller poller = pollerService.findAll().iterator().next();


			template("poller2", new Date(), "subject0", "body sample body", priority, poller, poller, null);

		}
		//Caso de uso negativo crear un mensaje sin actor que lo envia
		@Test
		public void negativeTest0() {

			Priority priority = new Priority();
			priority.setValue("LOW");

			Poller poller = pollerService.findAll().iterator().next();


			template("poller2", new Date(), "subject0", "body sample body", priority, null, poller, ConstraintViolationException.class);

		}

		@Test
		public void positiveTest2() {

			Priority priority = new Priority();
			priority.setValue("LOW");

			Poller poller = pollerService.findAll().iterator().next();
			

			template("dancer2", null, "subject0", "body sample body", priority, poller, poller, IllegalArgumentException.class);

		}
		
		@Test
		public void negativeTest3() {
			try {
				mailmessageService.send("hello", "body sample", "LOW", "falseMail@false.com");
			} catch (Exception ex) {
				checkExceptions(IllegalArgumentException.class, ex.getClass());
			}
		}

		// Ancillary methods ------------------------------------------------------
		protected void template(final String username, final Date sent, final String subject, final String body, final Priority priority, final Actor sender, final Actor recipient, final Class<?> expected) {
			Class<?> caught = null;

			try {
				authenticate(username);

				MailMessage mailmessage = mailmessageService.create();
				mailmessage.setSentMoment(sent);
				mailmessage.setSubject(subject);
				mailmessage.setBody(body);
				mailmessage.setPriority(priority);
				mailmessage.setSender(sender);
				mailmessage.setRecipient(recipient);

				mailmessageService.save(mailmessage);
				mailmessageService.flush();

				unauthenticate();
			} catch (final Throwable oops) {
				caught = oops.getClass();
			}

			checkExceptions(expected, caught);
		}

//	@Autowired
//	private MailMessageService	mailMessageService;
//	
//	@Test
//	public void driverListBill() {
//		final Object testingData1[][] = {
//			//  admin envia mensaje a poller
//			{
//				"admin","poller1","hola","hola", null
//			},
//			//  no logueado envia mensaje a poller
//			{
//				null,"poller1","hola","hola", IllegalArgumentException.class
//			},
//			// admin envia mensaje a nadie
//			{
//				"admin",null,"hola","hola", IllegalArgumentException.class
//			}
//		};
//
//		for (int i = 0; i < testingData1.length; i++)
//			this.ExchangeMessagesTest((String) testingData1[i][0],(String) testingData1[i][1],(String) testingData1[i][2],(String) testingData1[i][3], (Class<?>) testingData1[i][4]);
//
//	}
//	
//	protected void ExchangeMessagesTest(final String username, final String actorName,String subject,String body, final Class<?> expected) {
//		// TODO Auto-generated method stub
//		Class<?> caught;
//
//		caught = null;
//		try {
//
//			this.authenticate(username);
//			this.mailMessageService.send(subject, body, "NEUTRAL", actorName);
//			this.unauthenticate();
//
//		} catch (final Throwable oops) {
//			caught = oops.getClass();
//		}
//		this.checkExceptions(expected, caught);
//	}
//	
//	@Test
//	public void driverDeleteMessage() {
//		final Object testingData2[][] = {
//			//  admin borra mensaje existente
//			{
//				"admin",233, IllegalArgumentException.class
//			},
//			//  admin borra mensaje inexistente
//			{
//				"admin",666, IllegalArgumentException.class
//			},
//			// admin borra mensaje nulo
//			{
//				"admin",null, IllegalArgumentException.class
//			}
//		};
//
//		for (int i = 0; i < testingData2.length; i++)
//			this.DeleteMessageTest((String) testingData2[i][0],(Integer) testingData2[i][1], (Class<?>) testingData2[i][2]);
//
//	}
//	
//	protected void DeleteMessageTest(final String username, final Integer messageId, final Class<?> expected) {
//		// TODO Auto-generated method stub
//		Class<?> caught;
//
//		caught = null;
//		try {
//
//			this.authenticate(username);
//			MailMessage m = mailMessageService.findOne(messageId);
//			this.mailMessageService.delete(m);
//			this.unauthenticate();
//
//		} catch (final Throwable oops) {
//			caught = oops.getClass();
//		}
//		this.checkExceptions(expected, caught);
//	}
	
	
}
