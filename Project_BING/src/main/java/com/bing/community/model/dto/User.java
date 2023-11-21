package com.bing.community.model.dto;

public class User {
	private String email;
	private String pw;
	private String name;
	private String gender;
	private String nickname;
	private String withdraw_text;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String email, String pw, String name, String gender, String nickname, String withdraw_text) {
		super();
		this.email = email;
		this.pw = pw;
		this.name = name;
		this.gender = gender;
		this.nickname = nickname;
		this.withdraw_text = withdraw_text;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getWithdraw_text() {
		return withdraw_text;
	}

	public void setWithdraw_text(String withdraw_text) {
		this.withdraw_text = withdraw_text;
	}

}
