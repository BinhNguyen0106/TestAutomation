package Railway;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;

public class LoginAccountInactiveTest {

	User user;

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Pre-condition");

		user = new User();
		user.generateNewUserInfo();

		System.setProperty("webdriver.chrome.driver", Utilities.getProjectPath() + "\\Executables\\chromedriver.exe");
		Constant.WEBDRIVER = new ChromeDriver();
		Constant.WEBDRIVER.manage().window().maximize();

		HomePage homepage = new HomePage();
		homepage.open();

		RegisterPage registerpage = homepage.gotoRegisterPage().RegisterNewAccount(user);
		boolean actualRegister = registerpage.isRegisterSuccessfully();

		Assert.assertTrue(actualRegister, "Error when create new account");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("Post-condition");
		Constant.WEBDRIVER.quit();
	}

	@Test
	public void TC08() {
		System.out.println("TC08 - User can't login with an account hasn't been activated");

		HomePage homepage = new HomePage();
		homepage.open();

		LoginPage loginpage = homepage.gotoLoginPage().loginWithInActiveAccount(user);
		String actualMsg = loginpage.getLblLoginError();
		String expectedMsg = "Invalid username or password. Please try again.";

		Assert.assertEquals(actualMsg, expectedMsg, "Error message login form is not displayed as expected.");
	}
}
