package com.bing.community.model.dto;

public class User {
	private String email;
	private String pw;
	private int height;
	private int weight;
	private String name;
	private String gender;
	private String nickname;
	public User(String email, String pw, int height, int weight, String name, String gender, String nickname) {
		super();
		this.email = email;
		this.pw = pw;
		this.height = height;
		this.weight = weight;
		this.name = name;
		this.gender = gender;
		this.nickname = nickname;
	}

	public User() {
		// TODO Auto-generated constructor stub
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

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
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
	
}
