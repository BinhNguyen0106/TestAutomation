package Common;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import org.openqa.selenium.WebElement;

import Constant.Constant;
import Constant.SeatType;
import Constant.Station;

public class Utilities {

	public static String getProjectPath() {
		return System.getProperty("user.dir");
	}

	public static String generateStringRandom(int length) {
		String allowedChars = "abcdefghijklmnopqrstuvwxyz" + "1234567890" + "_-.";
		Random rand = new Random();
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int randIndex = rand.nextInt(allowedChars.length());
			res.append(allowedChars.charAt(randIndex));
		}
		return res.toString();
	}

	public static int getRandomNumber(int min, int max) {
		Random rand = new Random();
		return rand.nextInt(max - min + 1) + min;
	}

	public static boolean isDisplayed(WebElement element) {
		return element != null ? element.isDisplayed() : false;
	}

	public static WebElement findElement(By xPatth) {
		try {
			WebElement element = Constant.WEBDRIVER.findElement(xPatth);
			return element.isDisplayed() ? element : null;
		} catch (NoSuchElementException ex) {
			return null;
		}
	}

	public static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	public static String GetStationName(Station station) {
		switch (station) {
		case SAI_GON:
			return "Sài Gòn";
		case PHAN_THIET:
			return "Phan Thiết";
		case NHA_TRANG:
			return "Nha Trang";
		case DA_NANG:
			return "Đà Nẵng";
		case HUE:
			return "Huế";
		case QUANG_NGAI:
			return "Quảng Ngãi";
		}
		return "";
	}

	public static String GetSeatTypeName(SeatType stype) {
		switch (stype) {
		case HARD_SEAT:
			return "Hard seat";
		case SOFT_SEAT:
			return "Soft seat";
		case HARD_BED:
			return "Hard bed";
		case SOFT_BED:
			return "Soft bed";
		case SOFT_SEAT_WITH_AIR_CONDITIONER:
			return "Soft seat with air conditioner";
		case SOFT_BED_WITH_AIR_CONDITIONER:
			return "Soft bed with air conditioner";
		}
		return "";
	}
}
