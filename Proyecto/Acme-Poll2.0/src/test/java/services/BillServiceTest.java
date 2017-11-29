package services;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import security.LoginService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
		"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BillServiceTest extends AbstractTest{

	@Autowired
	private BillService	billService;
	
	@Test
	public void driverListBill() {
		final Object testingData1[][] = {
			//  Poller lista sus bills
			{
				"poller1", null
			},
			//  no logueado lista sus bills
			{
				null, IllegalArgumentException.class
			},
			//  administrador lista sus bills
			{
				"admin", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData1.length; i++)
			this.ListBillTest((String) testingData1[i][0], (Class<?>) testingData1[i][1]);

	}
	
	protected void ListBillTest(final String username, final Class<?> expected) {
		// TODO Auto-generated method stub
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);
			this.billService.list();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
	
	
	@Test
	public void driverAddReceiptTest() {
		final Object testingData1[][] = {
			//  Poller añade recibo a bill sin pagar
			{
				"poller1", "receipt",198,null
			},
			//  Poller añade recibo a bill pagada
			{
				"poller1", "receipt",199, ConstraintViolationException.class
			},
			//  administrador añade recibo a bill pagada
			{
				"admin","receipt",198, ConstraintViolationException.class
			}
		};

		for (int i = 0; i < testingData1.length; i++)
			this.AddReceiptTest((String) testingData1[i][0],(String) testingData1[i][1],(Integer) testingData1[i][2],(Class<?>) testingData1[i][3]);

	}
	
	protected void AddReceiptTest( String username, String receipt, Integer billId, Class<?> expected) {
		// TODO Auto-generated method stub
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);
			this.billService.addReceipt(billId, receipt);
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
	
//	
//	@Test
//	public void driverCreateBillTestTest() {
//		final Object testingData1[][] = {
//			//  Administrador crea bill
//			{
//				"admin", 20.20,new Date(),null
//			},
//			//  Administrador crea bill sin amount
//			{
//				null, null,new Date(), IllegalArgumentException.class
//			},
//			//  sin logueo crea bill
//			{
//				null,20.20,new Date(), IllegalArgumentException.class
//			}
//		};
//
//		for (int i = 0; i < testingData1.length; i++)
//			this.CreateBillTest((String) testingData1[i][0],(Double) testingData1[i][1],(Date) testingData1[i][2],(Class<?>) testingData1[i][3]);
//
//	}
//	
//	
//	protected void CreateBillTest( String username, Double amount, Date date, Class<?> expected) {
//		// TODO Auto-generated method stub
//		Class<?> caught;
//
//		caught = null;
//		try {
//
//			this.authenticate(username);
//			Bill b = new Bill();
//			Assert.isTrue(amount!=null && (amount instanceof Double) && date!=null && (date instanceof Date) && LoginService.isAnyAuthenticated() && LoginService.hasRole("ADMINISTRATOR"));
//			b.setAmount(amount);
//			b.setDate(date);
//			b.setEndorsed(false);
//			b.setPaid(false);
//			b.setPoll(new Poll());
//			this.billService.save(b);
//			this.unauthenticate();
//
//		} catch (final Throwable oops) {
//			caught = oops.getClass();
//		}
//		this.checkExceptions(expected, caught);
//	}
	
	
	@Test
	public void driverListBillEndorsed() {
		final Object testingData1[][] = {
			//  Admin lista bills
			{
				"admin", null
			},
			//  no logueado lista bills
			{
				null, IllegalArgumentException.class
			},
			//  poller lista  bills
			{
				"poller1", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData1.length; i++)
			this.ListBillEndorsedTest((String) testingData1[i][0], (Class<?>) testingData1[i][1]);

	}
	
	protected void ListBillEndorsedTest(final String username, final Class<?> expected) {
		// TODO Auto-generated method stub
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);
			Assert.isTrue(LoginService.isAnyAuthenticated() && LoginService.hasRole("ADMINISTRATOR"));
			this.billService.billsEndorsed();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
	
	
	
	@Test
	public void driverListBillPaid() {
		final Object testingData1[][] = {
			//  Admin lista bills
			{
				"admin", null
			},
			//  no logueado lista bills
			{
				null, IllegalArgumentException.class
			},
			//  poller lista  bills
			{
				"poller1", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData1.length; i++)
			this.ListBillPaidTest((String) testingData1[i][0], (Class<?>) testingData1[i][1]);

	}
	
	protected void ListBillPaidTest(final String username, final Class<?> expected) {
		// TODO Auto-generated method stub
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);
			Assert.isTrue(LoginService.isAnyAuthenticated() && LoginService.hasRole("ADMINISTRATOR"));
			this.billService.billsPaid();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
	
	
	@Test
	public void driverEndorsedBill() {
		final Object testingData1[][] = {
			//  Admin endorsed bill correcto
			{
				"admin", 199,null
			},
			//  Admin endorsed bill incorrecto
			{
				"admin", 198,IllegalArgumentException.class
			},
			//  poller endorsed bill
			{
				"poller1", 198,IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData1.length; i++)
			this.EndorsedBillTest((String) testingData1[i][0],(Integer) testingData1[i][1] ,(Class<?>) testingData1[i][2]);

	}
	
	protected void EndorsedBillTest(final String username,Integer billId, final Class<?> expected) {
		// TODO Auto-generated method stub
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);
			this.billService.endorse(billService.findOne(billId));;
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
}
