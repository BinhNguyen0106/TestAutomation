package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Common.Utilities;
import Constant.Constant;

public class LoginPage {

	// Locators
	private final By _txtUsername = By.xpath("//input[@id='username']");
	private final By _txtPassword = By.xpath("//input[@id='password']");
	private final By _btnLogin = By.xpath("//input[@value='login']");
	private final By _lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");
	private final By _lblUsernameErrorMsg = By.xpath("//label[@for='username' and @class='validation-error']");
	private final By _lbtTitlePage = By.xpath("//div[@id='content']/h1");
	private final By _linkForgotPasswordPage = By.xpath("//a[contains(text(),'Forgot Password page')]");
	private final By _txtEmailResetPassword = By.xpath("//input[@id='email']");
	private final By _btnSendInstructions = By.xpath("//input[@value='Send Instructions']");
	private final By _lbtTitleResetPWForm = By.xpath("//legend[contains(text(),'Password Change Form')]");
	private final By _btnResetPassword = By.xpath("//input[@value='Reset Password']");
	private final By _txtNewPassword = By.xpath("//input[@id='newPassword']");
	private final By _txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
	private final By _txtResetToken = By.xpath("//input[@id='resetToken']");
	private final By _lbtResetPasswordError = By.xpath("//p[@class='message error']");
	private final By _lbtToken = By.xpath("//label[@for='resetToken' and @class='validation-error']");
	private final By _lbtConfirmPassword = By.xpath("//label[@for='confirmPassword' and @class='validation-error']");
	private final By _lbtChangePwSuccess = By.xpath("//p[@class='message success']");

	// Elements
	protected WebElement getTxtUsername() {
		return Utilities.findElement(_txtUsername);
	}

	protected WebElement getTxtPassword() {
		return Utilities.findElement(_txtPassword);
	}

	protected WebElement getBtnLogin() {
		return Utilities.findElement(_btnLogin);
	}

	protected WebElement getLblLoginErrorMsg() {
		return Utilities.findElement(_lblLoginErrorMsg);
	}

	protected WebElement getLblUsernameErrorMsg() {
		return Utilities.findElement(_lblUsernameErrorMsg);
	}

	protected WebElement getLbtTitlePage() {
		return Utilities.findElement(_lbtTitlePage);
	}

	protected WebElement getLinkForgotPasswordPage() {
		return Utilities.findElement(_linkForgotPasswordPage);
	}

	protected WebElement getTxtEmailResetPassword() {
		return Utilities.findElement(_txtEmailResetPassword);
	}

	protected WebElement getBtnSendInstructions() {
		return Utilities.findElement(_btnSendInstructions);
	}

	protected WebElement getLbtTitleResetPWForm() {
		return Utilities.findElement(_lbtTitleResetPWForm);
	}

	protected WebElement getBtnResetPassword() {
		return Utilities.findElement(_btnResetPassword);
	}

	protected WebElement getTxtNewPassword() {
		return Utilities.findElement(_txtNewPassword);
	}

	protected WebElement getTxtConfirmPassword() {
		return Utilities.findElement(_txtConfirmPassword);
	}

	protected WebElement getTxtResetToken() {
		return Utilities.findElement(_txtResetToken);
	}

	protected WebElement getLbtResetPasswordError() {
		return Utilities.findElement(_lbtResetPasswordError);
	}

	protected WebElement getLbtToken() {
		return Utilities.findElement(_lbtToken);
	}

	protected WebElement getLbtConfirmPassword() {
		return Utilities.findElement(_lbtConfirmPassword);
	}

	protected WebElement getLbtChangePwSuccess() {
		return Utilities.findElement(_lbtChangePwSuccess);
	}

	// Methods
	private void FillUserAndClick(User user, String Type) {
		if (Type.equals("Success")) {
			this.getTxtUsername().sendKeys(user.getUsername());
			this.getTxtPassword().sendKeys(user.getPassword());
		} else if (Type.equals("BlankUsername")) {
			this.getTxtPassword().sendKeys(user.getPassword());
		} else if (Type.equals("InvalidPassword")) {
			this.getTxtUsername().sendKeys(user.getUsername());
			this.getTxtPassword().sendKeys(user.getNewpassword());
		}
		this.getBtnLogin().click();
	}

	public HomePage login(User user) {
		FillUserAndClick(user, "Success");
		return new HomePage();
	}

	public LoginPage loginFailedWithBlankUsername(User user) {
		FillUserAndClick(user, "BlankUsername");
		return new LoginPage();
	}

	public LoginPage loginFailedWithInvalidPassword(User user) {
		FillUserAndClick(user, "InvalidPassword");
		return new LoginPage();
	}

	public LoginPage loginWithInActiveAccount(User user) {
		FillUserAndClick(user, "Success");
		return new LoginPage();
	}

	public String getLblUsernameError() {
		return this.getLblUsernameErrorMsg().getText();
	}

	public String getLblLoginError() {
		return this.getLblLoginErrorMsg().getText();
	}

	public boolean isLoginPageDisplayed() {
		return (Utilities.isDisplayed(this.getLbtTitlePage()) & Utilities.isDisplayed(this.getBtnLogin()));
	}

	public LoginPage ClicktoFogotPasswordLink() {
		this.getLinkForgotPasswordPage().click();
		return new LoginPage();
	}

	public LoginPage GetResetEmailPassword(User user) {
		this.getTxtEmailResetPassword().sendKeys(user.getUsername());
		this.getBtnSendInstructions().click();
		return new LoginPage();
	}

	public LoginPage ResetPasswordAcount(String URL) {
		Constant.WEBDRIVER.navigate().to(URL);
		return this;
	}

	public boolean isPasswordChangeFormDisplayed() {
		return (Utilities.isDisplayed(this.getLbtTitleResetPWForm())
				& Utilities.isDisplayed(this.getBtnResetPassword()));
	}

	public LoginPage ChangePasswordWithOutToken(User user) {
		String newPassword = user.getNewpassword();
		this.getTxtNewPassword().sendKeys(newPassword);
		this.getTxtConfirmPassword().sendKeys(newPassword);
		this.getTxtResetToken().clear();
		this.getBtnResetPassword().click();
		return new LoginPage();
	}

	public String getLbtResetPasswordErrorText() {
		return this.getLbtResetPasswordError().getText();
	}

	public String getLbtTokenText() {
		return this.getLbtToken().getText();
	}

	public LoginPage ChangePasswordNotMatch(User user) {
		this.getTxtNewPassword().sendKeys(user.getNewpassword());
		this.getTxtConfirmPassword().sendKeys(user.getNewpassword());
		this.getBtnResetPassword().click();
		return new LoginPage();
	}

	public String getLbtConfirmPasswordText() {
		return this.getLbtConfirmPassword().getText();
	}

	public boolean isChangePasswordSuccess() {
		return Utilities.isDisplayed(this.getLbtChangePwSuccess());
	}
}
