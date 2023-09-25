package Railway;

import Common.Utilities;
import Constant.Constant;

public class User {

	private String _username;
	private String _password;
	private String _confirmpassword;
	private String _pid;

	public void initUser() {
		_username = Constant.USERNAME;
		_password = Constant.PASSWORD;
	}

	public String getUsername() {
		return _username;
	}

	public void setUsername(String _username) {
		this._username = _username;
	}

	public String getPassword() {
		return _password;
	}

	public void setPassword(String _password) {
		this._password = _password;
	}

	public String getConfirmpassword() {
		return _confirmpassword;
	}

	public void setConfirmpassword(String _confirmpassword) {
		this._confirmpassword = _confirmpassword;
	}

	public String getPid() {
		return _pid;
	}

	public void setPid(String _pid) {
		this._pid = _pid;
	}

	public String getNewpassword() {
		return Utilities.generateStringRandom(Utilities.getRandomNumber(8, 64));
	}

	public void generateNewUserInfo() {
		this._username = Utilities.generateStringRandom(Utilities.getRandomNumber(6, 20)) + "@gmail.com";
		this._password = Utilities.generateStringRandom(Utilities.getRandomNumber(8, 64));
		this._confirmpassword = _password;
		this._pid = Utilities.generateStringRandom(Utilities.getRandomNumber(8, 20));
	}
}
