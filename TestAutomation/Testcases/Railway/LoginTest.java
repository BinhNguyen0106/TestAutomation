package Railway;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;

public class LoginTest {

	User user;

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Pre-condition");

		user = new User();
		user.initUser();

		System.setProperty("webdriver.chrome.driver", Utilities.getProjectPath() + "\\Executables\\chromedriver.exe");
		Constant.WEBDRIVER = new ChromeDriver();
		Constant.WEBDRIVER.manage().window().maximize();
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("Post-condition");
		Constant.WEBDRIVER.quit();
	}

	@Test
	public void TC01() {
		System.out.println("TC01 - User can log into Railway with valid username and password");

		HomePage homepage = new HomePage();
		homepage.open();

		String actualMsg = homepage.gotoLoginPage().login(user).getWelcomeMessage();
		String expectedMsg = "Welcome " + user.getUsername();

		Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected.");
	}

	@Test
	public void TC02() {
		System.out.println("TC02 - User can't login with blank \"Username\" textbox");

		HomePage homepage = new HomePage();
		homepage.open();

		String actualMsg = homepage.gotoLoginPage().loginFailedWithBlankUsername(user).getLblLoginError();
		String expectedMsg = "There was a problem with your login and/or errors exist in your form.";

		Assert.assertEquals(actualMsg, expectedMsg, "Error message login form is not displayed as expected.");
	}

	@Test
	public void TC03() {
		System.out.println("TC03 - User cannot log into Railway with invalid password");

		HomePage homepage = new HomePage();
		homepage.open();

		String actualMsg = homepage.gotoLoginPage().loginFailedWithInvalidPassword(user).getLblLoginError();
		String expectedMsg = "There was a problem with your login and/or errors exist in your form.";

		Assert.assertEquals(actualMsg, expectedMsg, "Error message login form is not displayed as expected.");
	}

	@Test
	public void TC04() {
		System.out.println("TC04 - Login page displays when un-logged User clicks on \"Book ticket\" tab");

		HomePage homepage = new HomePage();
		homepage.open();

		boolean actualLoginPageDisplay = homepage.gotoBookTicketWithOutLogin().isLoginPageDisplayed();

		Assert.assertTrue(actualLoginPageDisplay, "Login page must displays instead of Book ticket page.");

	}

	@Test
	public void TC05() {
		System.out.println("TC05 - System shows message when user enters wrong password several times");

		HomePage homepage = new HomePage();
		homepage.open();

		LoginPage loginPage = homepage.gotoLoginPage();

		int LoginTime = 4;
		for (int i = 0; i < LoginTime; i++) {
			loginPage.loginFailedWithInvalidPassword(user);
			loginPage.getTxtUsername().clear();
		}

		String actualMsg = loginPage.getLblLoginError();
		String expectedMsg = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";

		Assert.assertEquals(actualMsg, expectedMsg, "Error message login form is not displayed as expected.");
	}

	@Test
	public void TC06() {
		System.out.println("TC06 - Additional pages display once user logged in");

		HomePage homepage = new HomePage();
		homepage.open();

		GeneralPage generalpage = homepage.gotoLoginPage().login(user);
		boolean actuaDisplayTab = generalpage.is_LogoutTab_ChangePasswordTab_MyTicketTab_Displayed();

		Assert.assertTrue(actuaDisplayTab, "My Ticket Tab, Change Password tab and Logout Tab is not displayed.");

		boolean actualMyTicketPage = generalpage.gotoMyTicketPage().isMyTicketPageDisplayed();

		Assert.assertTrue(actualMyTicketPage, "My Ticket Page is not displayed");

		boolean actualChangePasswordPage = generalpage.gotoChangePasswordPage().isChangePasswordPageDisplayed();

		Assert.assertTrue(actualChangePasswordPage, "Change Password Page is not displayed");
	}
}
