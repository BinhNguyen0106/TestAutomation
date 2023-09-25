package Railway;

//import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;

public class RegisterPage {

	// Locators
	private final By _txtEmail = By.xpath("//input[@id='email']");
	private final By _txtPassword = By.xpath("//input[@id='password']");
	private final By _txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
	private final By _txtPid = By.xpath("//input[@id='pid']");
	private final By _btnRegiter = By.xpath("//input[@value='Register']");
	private final By _lblThank = By.xpath("//h1[contains(text(),'Thank you')]");
	private final By _msgError = By.xpath("//p[@class='message error']");
	private final By _lbtConfirmPassword = By.xpath("//label[@for='confirmPassword' and @class='validation-error']");
	private final By _lbtActiveAccountSuccess = By
			.xpath("//p[contains(text(),'Registration Confirmed! You can now log in to the site.')]");
	private final By _lbtTxtPassword = By.xpath("//label[@for='password' and @class='validation-error']");
	private final By _lbtTxtPIDNumber = By.xpath("//label[@for='pid' and @class='validation-error']");

	// Elements
	protected WebElement getTxtEmail() {
		return Utilities.findElement(_txtEmail);
	}

	protected WebElement getTxtPassword() {
		return Utilities.findElement(_txtPassword);
	}

	protected WebElement getTxtConfirmPassword() {
		return Utilities.findElement(_txtConfirmPassword);
	}

	protected WebElement getTxtPid() {
		return Utilities.findElement(_txtPid);
	}

	protected WebElement getLbtThank() {
		return Utilities.findElement(_lblThank);
	}

	protected WebElement getBtnRegiter() {
		return Utilities.findElement(_btnRegiter);
	}

	protected WebElement getMsgError() {
		return Utilities.findElement(_msgError);

	}

	protected WebElement getLbtConfirmPassword() {
		return Utilities.findElement(_lbtConfirmPassword);

	}

	protected WebElement getLbtActiveAccountSuccess() {
		return Utilities.findElement(_lbtActiveAccountSuccess);

	}

	protected WebElement getLbtLbtTxtPassword() {
		return Utilities.findElement(_lbtTxtPassword);

	}

	protected WebElement getLbtTxtPIDNumber() {
		return Utilities.findElement(_lbtTxtPIDNumber);

	}

	// Methods
	private void FillUserAndClick(User user, String Type) {
		this.getTxtEmail().sendKeys(user.getUsername());
		this.getTxtPassword().sendKeys(user.getPassword());
		this.getTxtPid().sendKeys(user.getPid());
		if (Type.equals("")) {
			this.getTxtConfirmPassword().sendKeys(user.getConfirmpassword());

		} else if (Type.equals("PassNotSame")) {
			this.getTxtConfirmPassword().sendKeys("Confirm_Password_is_not_same");
		}
		this.getBtnRegiter().click();
	}

	public RegisterPage RegisterNewAccount(User user) {
		FillUserAndClick(user, "");
		return new RegisterPage();
	}

	public RegisterPage RegisterWithPassNotSame(User user) {
		FillUserAndClick(user, "PassNotSame");
		return new RegisterPage();
	}

	public RegisterPage RegisterWithEmptyFields(User user) {
		this.getTxtEmail().sendKeys(user.getUsername());
		this.getBtnRegiter().click();
		return new RegisterPage();
	}

	public boolean isRegisterSuccessfully() {
		return !Utilities.isDisplayed(this.getBtnRegiter());

	}

	public String getLblThank() {
		return this.getLbtThank().getText();
	}

	public String getMsgErrorText() {
		return this.getMsgError().getText();
	}

	public String getLbtConfirmPasswordText() {
		return this.getLbtConfirmPassword().getText();
	}

	public String getLbtLbtTxtPasswordText() {
		return this.getLbtLbtTxtPassword().getText();
	}

	public String getLbtTxtPIDNumberText() {
		return this.getLbtTxtPIDNumber().getText();
	}

	public RegisterPage ActiveAcount(String URL) {
		Constant.WEBDRIVER.navigate().to(URL);
		return this;
	}

	public boolean isActiveAccountSuccessfully() {
		return Utilities.isDisplayed(this.getLbtActiveAccountSuccess());
	}
}
