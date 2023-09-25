package Railway;
//test

import java.text.SimpleDateFormat;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Common.Utilities;

public class BookTicketPage {

	String DateFormat = "M/d/yyyy";
	SimpleDateFormat SDF;

	// Locators
	private final By _drpDepartDate = By.xpath("//select[@name='Date']");
	private final By _drpDepartFrom = By.xpath("//select[@name='DepartStation']");
	private final By _drpArriveAt = By.xpath("//select[@name='ArriveStation']");
	private final By _drpSeatType = By.xpath("//select[@name='SeatType']");
	private final By _drpTicketAmount = By.xpath("//select[@name='TicketAmount']");
	private final By _btnBookTicket = By.xpath("//input[@type='submit' and @value='Book ticket']");
	private final By _lbtBookTicket = By.xpath("//h1[contains(text(),'booked')]");

	private final By _getCellByColumn(int row, String column) {
		return By.xpath("//table[@class='MyTable WideTable']//tr[" + row + "]/td[count(//th[text()='" + column
				+ "']/preceding-sibling::th) +1]");
	}

	// Elements
	protected Select getDrpDepartDate() {
		return new Select(Utilities.findElement(_drpDepartDate));
	}

	protected Select getDrpDepartFrom() {
		return new Select(Utilities.findElement(_drpDepartFrom));
	}

	protected Select getDrpArriveAt() {
		return new Select(Utilities.findElement(_drpArriveAt));
	}

	protected Select getDrpSeatType() {
		return new Select(Utilities.findElement(_drpSeatType));
	}

	protected Select getDrpTicketAmounte() {
		return new Select(Utilities.findElement(_drpTicketAmount));
	}

	protected WebElement getBtnBookTicket() {
		return Utilities.findElement(_btnBookTicket);
	}

	protected WebElement getLbtBookTicket() {
		return Utilities.findElement(_lbtBookTicket);
	}

	protected WebElement getGetCellByColumn(int row, String column) {
		return Utilities.findElement(_getCellByColumn(row, column));
	}

	// Methods
	public BookTicketPage BookTicket(Ticket ticket) {
		SDF = new SimpleDateFormat(DateFormat);
		String departDate = SDF.format(ticket.getDepartdate()).toString();
		this.getDrpDepartDate().selectByVisibleText(departDate);
		this.getDrpDepartFrom().selectByVisibleText(Utilities.GetStationName(ticket.getDepartfrom()));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.getDrpArriveAt().selectByVisibleText(Utilities.GetStationName(ticket.getArriveat()));
		this.getDrpSeatType().selectByVisibleText(Utilities.GetSeatTypeName(ticket.getSeattype()));
		this.getDrpTicketAmounte().selectByVisibleText(String.valueOf(ticket.getTicketamount()));
		this.getBtnBookTicket().click();
		return new BookTicketPage();
	}

	public BookTicketPage BookListTicket(int count) {
		if (count <= 5) {
			this.getDrpDepartFrom().selectByIndex(count);
		} else {

			this.getDrpDepartFrom().selectByIndex(count -= count);
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.getBtnBookTicket().click();

		return new BookTicketPage();
	}

	public String getLbtBookTicketText() {
		return this.getLbtBookTicket().getText();
	}

	public String getDepartDate() {
		return this.getGetCellByColumn(2, "Depart Date").getText();
	}

	public String getDepartStation() {
		return this.getGetCellByColumn(2, "Depart Station").getText();
	}

	public String getArriveStation() {
		return this.getGetCellByColumn(2, "Arrive Station").getText();
	}

	public String getSeatType() {
		return this.getGetCellByColumn(2, "Seat Type").getText();
	}

	public String getAmount() {
		return this.getGetCellByColumn(2, "Amount").getText();
	}

	public String getDrpDepartFromText() {
		return this.getDrpDepartFrom().getFirstSelectedOption().getText();
	}

	public String getDrpArriveAtText() {
		return this.getDrpArriveAt().getFirstSelectedOption().getText();
	}

	public boolean isInformationTicketCorrectly(Ticket ticket) {
		SDF = new SimpleDateFormat(DateFormat);
		return getDepartDate().equals(SDF.format(ticket.getDepartdate()).toString())
				& getDepartStation().equals(Utilities.GetStationName(ticket.getDepartfrom()))
				& getArriveStation().equals(Utilities.GetStationName(ticket.getArriveat()))
				& getSeatType().equals(Utilities.GetSeatTypeName(ticket.getSeattype()))
				& getAmount().equals(String.valueOf(ticket.getTicketamount()));
	}

	public boolean isStationNameCorrectly(Ticket ticket) {
		return this.getDrpDepartFromText().equals(Utilities.GetStationName(ticket.getDepartfrom()))
				& this.getDrpArriveAtText().equals(Utilities.GetStationName(ticket.getArriveat()));
	}
}
