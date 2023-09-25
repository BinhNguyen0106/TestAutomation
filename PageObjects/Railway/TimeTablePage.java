package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Common.Utilities;

public class TimeTablePage {

	// Locators
	private final By _getCellByColumn(Ticket ticket, String link) {
		return By.xpath("//td[text()='" + Utilities.GetStationName(ticket.getDepartfrom())
				+ "']/following-sibling::td[text()='" + Utilities.GetStationName(ticket.getArriveat())
				+ "']/following-sibling::td/a[text()='" + link + "']");
	}

	// Elements
	protected WebElement getGetCellByColumn(Ticket ticket, String link) {
		return Utilities.findElement(_getCellByColumn(ticket, link));
	}

	// Methods
	public BookTicketPage gotoBookTicketPage(Ticket ticket, String link) {
		this.getGetCellByColumn(ticket, link).click();
		return new BookTicketPage();
	}

}
