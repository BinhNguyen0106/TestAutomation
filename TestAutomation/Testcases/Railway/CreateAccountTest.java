package Railway;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;

public class CreateAccountTest {

	User user;

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Pre-condition");

		user = new User();
		user.generateNewUserInfo();

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
	public void TC07() {
		System.out.println("TC07 - User can create new account");

		HomePage homepage = new HomePage();
		homepage.open();

		RegisterPage registerpage = homepage.gotoRegisterPage().RegisterNewAccount(user);
		String actualThankText = registerpage.getLblThank();
		String expectedThankText = "Thank you for registering your account";

		Assert.assertEquals(actualThankText, expectedThankText, "Message is not displayed as expected.");
	}

	@Test
	public void TC10() {
		System.out.println(
				"TC10 - User can't create account with \"Confirm password\" is not the same with \"Password\"");

		HomePage homepage = new HomePage();
		homepage.open();

		RegisterPage registerpage = homepage.gotoRegisterPage().RegisterWithPassNotSame(user);

		String actualErrorMsg = registerpage.getMsgErrorText();
		String expectedMsg = "There're errors in the form. Please correct the errors and try again.";

		Assert.assertEquals(actualErrorMsg, expectedMsg, "Error Message is not displayed as expected.");
	}

	@Test
	public void TC11() {
		System.out.println("TC11 - User can't create account while password and PID fields are empty");

		HomePage homepage = new HomePage();
		homepage.open();

		RegisterPage registerpage = homepage.gotoRegisterPage().RegisterWithEmptyFields(user);

		String actualErrorMsg = registerpage.getMsgErrorText();
		String expectedErrorMsg = "There're errors in the form. Please correct the errors and try again.";

		Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Message error is not displayed as expected.");

		String actualPasswordMsg = registerpage.getLbtLbtTxtPasswordText();
		String expectedPasswordMsg = "Invalid password length.";

		Assert.assertEquals(actualPasswordMsg, expectedPasswordMsg, "Message error is not displayed as expected.");

		String actualPIDMsg = registerpage.getLbtTxtPIDNumberText();
		String expectedPIDMsg = "Invalid ID length.";

		Assert.assertEquals(actualPIDMsg, expectedPIDMsg, "Message error is not displayed as expected.");
	}
}
