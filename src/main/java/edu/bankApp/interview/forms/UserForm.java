package edu.bankApp.interview.forms;

public class UserForm {

	private String userName;
	private String password;
	private String corpId;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	@Override
	public String toString() {
		return "UserForm [userName=" + userName + ", password=" + password + ", corpId=" + corpId + "]";
	}

}
