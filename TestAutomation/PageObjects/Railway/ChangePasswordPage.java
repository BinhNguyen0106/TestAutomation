package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Common.Utilities;
import Constant.Constant;

public class ChangePasswordPage {

	// Locators
	private final By _lbtTitlePage = By.xpath("//h1[contains(text(),'Change password')]");
	private final By _btnChangePassword = By.xpath("//input[@value='Change Password']");
	private final By _txtCurrentPw = By.xpath("//input[@id='currentPassword']");
	private final By _txtNewPw = By.xpath("//input[@id='newPassword']");
	private final By _txtConfirmPW = By.xpath("//input[@id='confirmPassword']");
	private final By _lbtChangePwSuccess = By.xpath("//p[@class='message success']");

	// Elements
	protected WebElement getLbtTitlePage() {
		return Utilities.findElement(_lbtTitlePage);
	}

	protected WebElement getBtnChangePassword() {
		return Utilities.findElement(_btnChangePassword);
	}

	protected WebElement getTxtCurrentPw() {
		return Utilities.findElement(_txtCurrentPw);
	}

	protected WebElement getTxtNewPw() {
		return Utilities.findElement(_txtNewPw);
	}

	protected WebElement getTxtConfirmPW() {
		return Utilities.findElement(_txtConfirmPW);
	}

	protected WebElement getLbtChangePwSuccess() {
		return Utilities.findElement(_lbtChangePwSuccess);
	}

	// Methods
	public boolean isChangePasswordPageDisplayed() {
		return (Utilities.isDisplayed(this.getLbtTitlePage()) & Utilities.isDisplayed(getLbtTitlePage()));
	}

	public ChangePasswordPage ChangePassword(User user) {
		this.getTxtCurrentPw().sendKeys(user.getPassword());
		String newPassword = user.getNewpassword();
		this.getTxtNewPw().sendKeys(newPassword);
		this.getTxtConfirmPW().sendKeys(newPassword);
		this.getBtnChangePassword().click();
		return new ChangePasswordPage();
	}

	public String getLbtChangePwSuccessText() {
		return this.getLbtChangePwSuccess().getText();
	}

	public ChangePasswordPage ResetPasswordAcount(String URL) {
		Constant.WEBDRIVER.navigate().to(URL);
		return this;
	}
}
