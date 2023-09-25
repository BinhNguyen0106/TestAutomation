package Railway;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Common.GetCodeActiveByAPI;
import Common.Utilities;
import Constant.Constant;
import Constant.Station;

public class ManageTicketTest {

	User user;
	GetCodeActiveByAPI getcode;
	Ticket ticket;
	String DateFormat = "MM/dd/yyyy";
	SimpleDateFormat SDF;

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
	public void FTTC01() {
		System.out.println("FTTC01 - User can filter \"Manager ticket\" table with Depart Station");

		HomePage homepage = new HomePage();
		homepage.open();

		homepage.gotoLoginPage().login(user);
		int count = 7;
		for (int i = 0; i <= count; i++) {
			homepage.gotoBookTicketPage().BookListTicket(i);
		}

		MyTicketPage myticketpage = homepage.gotoMyTicketPage();
		ticket = new Ticket();
		ticket.setDepartfrom(Station.DA_NANG);
		boolean actualDepartStationName = myticketpage.FilterDepartStationName(ticket)
				.isFilterDepartNameCorrectly(ticket);

		Assert.assertTrue(actualDepartStationName, "\"Manage ticket\" table shows incorrect ticket(s).");
	}

	@Test
	public void FTTC02() {

		System.out.println("FTTC02 -Error displays when user applies filter with invalid format for \"Depart Date\"");

		HomePage homepage = new HomePage();
		homepage.open();

		homepage.gotoLoginPage().login(user);
		int count = 7;
		for (int i = 0; i <= count; i++) {
			homepage.gotoBookTicketPage().BookListTicket(i);
		}

		MyTicketPage myticketpage = homepage.gotoMyTicketPage().FilterWrongDepartDate();

		Date date = new Date();
		SDF = new SimpleDateFormat(DateFormat);
		String Today = SDF.format(date).toString();

		String actualDateMsg = myticketpage.getLbtMsgDateText();
		String expectedDateMsg = "The date format is wrong, date filter is ignored. Example of a proper date: Today is "
				+ Today;

		Assert.assertEquals(actualDateMsg, expectedDateMsg, "Error message format date is not displayed as expected.");

	}
}
