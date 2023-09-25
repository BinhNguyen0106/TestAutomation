package Railway;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Common.GetCodeActiveByAPI;
import Common.Utilities;
import Constant.Constant;

public class CreateActiveAccountTest {

	User user;
	GetCodeActiveByAPI getcode;

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
	public void TC09() {
		System.out.println("TC09 - User can change password");

		HomePage homepage = new HomePage();
		homepage.open();

		String actualMsg = homepage.gotoLoginPage().login(user).gotoChangePasswordPage().ChangePassword(user)
				.getLbtChangePwSuccessText();
		String expectedMsg = "Your password has been updated";

		Assert.assertEquals(actualMsg, expectedMsg, "Change password message is not displayed as expected.");
	}
}
