package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Common.Utilities;

public class GeneralPage {

	// Locators
	private final By _tabLogin = By.xpath("//div[@id='menu']//span[contains(text(),'Login')]");
	private final By _tabLogout = By.xpath("//div[@id='menu']//span[contains(text(),'Log out')]");
	private final By _lbtWelcomeMessage = By.xpath("//div[@class='account']/strong");
	private final By _tabBookTicket = By.xpath("//div[@id='menu']//span[contains(text(),'Book ticket')]");
	private final By _tabMyTicket = By.xpath("//div[@id='menu']//span[contains(text(),'My ticket')]");
	private final By _tabChangePassword = By.xpath("//div[@id='menu']//span[contains(text(),'Change password')]");
	private final By _tabRegister = By.xpath("//div[@id='menu']//span[contains(text(),'Register')]");
	private final By _tabTimeTable = By.xpath("//div[@id='menu']//span[contains(text(),'Timetable')]");

	// Elements
	protected WebElement getTabLogin() {
		return Utilities.findElement(_tabLogin);
	}

	protected WebElement getTabLogout() {
		return Utilities.findElement(_tabLogout);
	}

	protected WebElement lbtWelcomeMessage() {
		return Utilities.findElement(_lbtWelcomeMessage);
	}

	protected WebElement getTabBookTicket() {
		return Utilities.findElement(_tabBookTicket);
	}

	protected WebElement getTabMyTicket() {
		return Utilities.findElement(_tabMyTicket);
	}

	protected WebElement getTabChangePassword() {
		return Utilities.findElement(_tabChangePassword);
	}

	protected WebElement getTabRegister() {
		return Utilities.findElement(_tabRegister);
	}

	protected WebElement getTabTimeTable() {
		return Utilities.findElement(_tabTimeTable);
	}

	// Methods
	public String getWelcomeMessage() {
		return this.lbtWelcomeMessage().getText();
	}

	public LoginPage gotoLoginPage() {
		this.getTabLogin().click();
		return new LoginPage();
	}

	public LoginPage gotoBookTicketWithOutLogin() {
		this.getTabBookTicket().click();
		return new LoginPage();
	}

	public boolean is_LogoutTab_ChangePasswordTab_MyTicketTab_Displayed() {
		return (Utilities.isDisplayed(this.getTabLogout()) & Utilities.isDisplayed(this.getTabChangePassword())
				& Utilities.isDisplayed(this.getTabMyTicket()));

	}

	public MyTicketPage gotoMyTicketPage() {
		this.getTabMyTicket().click();
		return new MyTicketPage();
	}

	public ChangePasswordPage gotoChangePasswordPage() {
		this.getTabChangePassword().click();
		return new ChangePasswordPage();
	}

	public RegisterPage gotoRegisterPage() {
		this.getTabRegister().click();
		return new RegisterPage();
	}

	public BookTicketPage gotoBookTicketPage() {
		this.getTabBookTicket().click();
		return new BookTicketPage();
	}

	public TimeTablePage gotoTimeTablePage() {
		this.getTabTimeTable().click();
		return new TimeTablePage();
	}
}
