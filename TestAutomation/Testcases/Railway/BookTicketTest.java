package Railway;

import java.util.Date;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Common.GetCodeActiveByAPI;
import Common.Utilities;
import Constant.Constant;
import Constant.SeatType;
import Constant.Station;

public class BookTicketTest {

	User user;
	GetCodeActiveByAPI getcode;
	Ticket ticket;

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Pre-condition");

		user = new User();
		user.generateNewUserInfo();
		getcode = new GetCodeActiveByAPI();

		System.setProperty("webdriver.chrome.driver", Utilities.getProjectPath() + "\\Executables\\chromedriver.exe");
		Constant.WEBDRIVER = new ChromeDriver();
		Constant.WEBDRIVER.manage().window().maximize();

		HomePage homepage = new HomePage();
		homepage.open();

		RegisterPage registerpage = homepage.gotoRegisterPage().RegisterNewAccount(user);
		boolean actualRegister = registerpage.isRegisterSuccessfully();

		Assert.assertTrue(actualRegister, "Error when create new account");

		System.out.println("New user name: " + user.getUsername());
		System.out.println("New user password: " + user.getPassword());
		System.out.println("Waiting to get active link...");

		String ActiveLink = getcode.GetLinkActive("Please confirm your account " + user.getUsername());
		System.out.println("Active acount link: " + ActiveLink);
		boolean actualActive = registerpage.ActiveAcount(ActiveLink).isActiveAccountSuccessfully();

		Assert.assertTrue(actualActive, "Error when active new account");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("Post-condition");
		Constant.WEBDRIVER.quit();
	}

	@Test
	public void TC14() {
		System.out.println("TC14 - User can book 1 ticket at a time");

		HomePage homepage = new HomePage();
		homepage.open();

		Date departdate = new Date();
		departdate = Utilities.addDays(departdate, 5);

		ticket = new Ticket(departdate, Station.SAI_GON, Station.NHA_TRANG, SeatType.SOFT_SEAT_WITH_AIR_CONDITIONER, 1);
		BookTicketPage bookticketpage = homepage.gotoLoginPage().login(user).gotoBookTicketPage().BookTicket(ticket);

		String actualMsg = bookticketpage.getLbtBookTicketText();
		String expectedMsg = "Ticket booked successfully!";

		Assert.assertEquals(actualMsg, expectedMsg, "Message ticket booked is not displayed as expected.");

		boolean actualTicket = bookticketpage.isInformationTicketCorrectly(ticket);

		Assert.assertTrue(actualTicket, "Ticket information display not correctly.");
	}

	@Test
	public void TC15() {
		System.out.println(
				"TC15 - User can open \"Book ticket\" page by clicking on \"Book ticket\" link in \"Train timetable\" page");

		HomePage homepage = new HomePage();
		homepage.open();

		ticket = new Ticket();
		ticket.setDepartfrom(Station.HUE);
		ticket.setArriveat(Station.SAI_GON);

		boolean actualStationName = homepage.gotoLoginPage().login(user).gotoTimeTablePage()
				.gotoBookTicketPage(ticket, "book ticket").isStationNameCorrectly(ticket);
		
		Assert.assertTrue(actualStationName,
				"'\"Book ticket\" page is loaded with incorrect  \"Depart from\" and \"Arrive at\" values.");
	}

	@Test
	public void TC16() {
		System.out.println("TC16 - User can cancel a ticket");

		HomePage homepage = new HomePage();
		homepage.open();

		Date departdate = new Date();
		departdate = Utilities.addDays(departdate, 5);

		ticket = new Ticket(departdate, Station.SAI_GON, Station.NHA_TRANG, SeatType.SOFT_SEAT_WITH_AIR_CONDITIONER, 1);
		homepage.gotoLoginPage().login(user).gotoBookTicketPage().BookTicket(ticket);
		boolean actualCancelTicket = homepage.gotoMyTicketPage().cancelTicket(Constant.WEBDRIVER).isLbtMyTicketMsg();

		Assert.assertTrue(actualCancelTicket, "The canceled ticket is not disappeared");
	}
}
