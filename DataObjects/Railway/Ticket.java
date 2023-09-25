package Railway;

import java.util.Date;

import Constant.SeatType;
import Constant.Station;

public class Ticket {

	private Date _departdate;
	private Station _departfrom;
	private Station _arriveat;
	private SeatType _seattype;
	private int _ticketamount;

	public Date getDepartdate() {
		return _departdate;
	}

	public void setDepartdate(Date _departdate) {
		this._departdate = _departdate;
	}

	public Station getDepartfrom() {
		return _departfrom;
	}

	public void setDepartfrom(Station _departfrom) {
		this._departfrom = _departfrom;
	}

	public Station getArriveat() {
		return _arriveat;
	}

	public void setArriveat(Station _arriveat) {
		this._arriveat = _arriveat;
	}

	public SeatType getSeattype() {
		return _seattype;
	}

	public void setSeattype(SeatType _seattype) {
		this._seattype = _seattype;
	}

	public int getTicketamount() {
		return _ticketamount;
	}

	public void seTicketamount(int _ticketamount) {
		this._ticketamount = _ticketamount;
	}

	public Ticket(Date _departdate, Station _departfrom, Station _arriveat, SeatType _seattype, int _ticketamount) {
		super();
		this._departdate = _departdate;
		this._departfrom = _departfrom;
		this._arriveat = _arriveat;
		this._seattype = _seattype;
		this._ticketamount = _ticketamount;
	}

	public Ticket() {

	}

}
