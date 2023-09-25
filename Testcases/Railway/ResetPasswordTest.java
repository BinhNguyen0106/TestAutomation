package Railway;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Common.GetCodeActiveByAPI;
import Common.Utilities;
import Constant.Constant;

public class ResetPasswordTest {

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
	public void TC12() {
		System.out.println("TC12 - Errors display when password reset token is blank");

		HomePage homepage = new HomePage();
		homepage.open();

		LoginPage loginpage = homepage.gotoLoginPage().ClicktoFogotPasswordLink().GetResetEmailPassword(user);
		System.out.println("Waiting to get reset link...");
		String ActiveLink = getcode.GetLinkActive("Please reset your password " + user.getUsername());

		loginpage.ResetPasswordAcount(ActiveLink);

		boolean actualResetFormDisplayed = loginpage.isPasswordChangeFormDisplayed();

		Assert.assertTrue(actualResetFormDisplayed, "\"Password Change Form\" page isn't displays");

		loginpage.ChangePasswordWithOutToken(user);

		boolean actualChangePasswordSuccess = loginpage.isChangePasswordSuccess();

		Assert.assertTrue(!actualChangePasswordSuccess, "The user can change password after clear reset token");

		String actualFormMsg = loginpage.getLbtResetPasswordErrorText();
		String expectedFormMsg = "The password reset token is incorrect or may be expired. Visit the forgot password page to generate a new one.";

		Assert.assertEquals(actualFormMsg, expectedFormMsg, "Message error is not displayed as expected.");

		String actualTokenMsg = loginpage.getLbtTokenText();
		String expectedTokenMsg = "The password reset token is invalid.";

		Assert.assertEquals(actualTokenMsg, expectedTokenMsg, "Message error is not displayed as expected.");
	}

	@Test
	public void TC13() {
		System.out
				.println("TC13 - Errors display if password and confirm password don't match when resetting password");

		HomePage homepage = new HomePage();
		homepage.open();

		LoginPage loginpage = homepage.gotoLoginPage().ClicktoFogotPasswordLink().GetResetEmailPassword(user);

		System.out.println("Waiting to get reset link...");

		String ActiveLink = getcode.GetLinkActive("Please reset your password " + user.getUsername());
		loginpage.ResetPasswordAcount(ActiveLink);

		boolean actualResetFormDisplayed = loginpage.isPasswordChangeFormDisplayed();

		Assert.assertTrue(actualResetFormDisplayed, "\"Password Change Form\" page isn't displays");

		loginpage.ChangePasswordNotMatch(user);

		String actualFormMsg = loginpage.getLbtResetPasswordErrorText();
		String expectedFormMsg = "Could not reset password. Please correct the errors and try again.";

		Assert.assertEquals(actualFormMsg, expectedFormMsg, "Message error is not displayed as expected.");

		String actualConfirmPassword = loginpage.getLbtConfirmPasswordText();
		String expectedConfirmMsg = "The password confirmation did not match the new password.";

		Assert.assertEquals(actualConfirmPassword, expectedConfirmMsg, "Message error is not displayed as expected.");
	}

}
