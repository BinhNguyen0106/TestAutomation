package Railway;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Common.Utilities;

public class MyTicketPage {

	// Locators
	private final By _lbtTitlePage = By.xpath("//h1[contains(text(),'Manage ticket')]");
	private final By _tabMyTicket = By.xpath("//div[@id='menu']//span[contains(text(),'My ticket')]//ancestor::li");
	private final By _lbtMyTicketMsg = By.xpath("//div[@class='error message']");
	private final By _getCellByColumn = By
			.xpath("//table[@class='MyTable']//tr/td[count(//th[text()='Operation']/preceding-sibling::th) +1]");
	private final By _getCellDepartStation = By
			.xpath("//table[@class='MyTable']//tr/td[count(//th[text()='Depart Station']/preceding-sibling::th) +1]");
	private final By _drpFilterDepartStation = By.xpath("//select[@name='FilterDpStation']");
	private final By _btnApplyFilter = By.xpath("//input[@value='Apply filter']");
	private final By _txtFilterDate = By.xpath("//input[@name='FilterDpDate']");
	private final By _lbtMsgDate = By.xpath("//div[@class='error message']");

	// Elements
	protected WebElement getLbtTitlePage() {
		return Utilities.findElement(_lbtTitlePage);
	}

	protected WebElement getCellCancel() {
		return Utilities.findElement(_getCellByColumn);
	}

	protected WebElement getbtMyTicketMsg() {
		return Utilities.findElement(_lbtMyTicketMsg);
	}

	protected WebElement getCellDepartStation() {
		return Utilities.findElement(_getCellDepartStation);
	}

	protected Select getDrpFilterDepartStation() {
		return new Select(Utilities.findElement(_drpFilterDepartStation));
	}

	protected WebElement getBtnApplyFilter() {
		return Utilities.findElement(_btnApplyFilter);
	}

	protected WebElement getTxtFilterDate() {
		return Utilities.findElement(_txtFilterDate);
	}

	protected WebElement getLbtMsgDate() {
		return Utilities.findElement(_lbtMsgDate);
	}

	// Methods
	public boolean isMyTicketPageDisplayed() {
		return Utilities.isDisplayed(getLbtTitlePage()) & isMyTicketTabSelected();
	}

	public boolean isMyTicketTabSelected() {
		return Utilities.findElement(_tabMyTicket).getAttribute("class").equals("selected") ? true : false;
	}

	public boolean isLbtMyTicketMsg() {
		return Utilities.isDisplayed(this.getbtMyTicketMsg());
	}

	public MyTicketPage cancelTicket(WebDriver driver) {
		this.getCellCancel().click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		return new MyTicketPage();
	}

	public String getCellDepartStationText() {
		return this.getCellDepartStation().getText();
	}

	public MyTicketPage FilterDepartStationName(Ticket ticket) {
		this.getDrpFilterDepartStation().selectByVisibleText(Utilities.GetStationName(ticket.getDepartfrom()));
		this.getBtnApplyFilter().click();
		return new MyTicketPage();
	}

	public boolean isFilterDepartNameCorrectly(Ticket ticket) {
		return this.getCellDepartStationText().equals(Utilities.GetStationName(ticket.getDepartfrom()));
	}

	public String getLbtMsgDateText() {
		return this.getLbtMsgDate().getText();
	}

	public MyTicketPage FilterWrongDepartDate() {
		this.getTxtFilterDate().sendKeys(Utilities.generateStringRandom(10));
		this.getBtnApplyFilter().click();
		return new MyTicketPage();
	}
}
