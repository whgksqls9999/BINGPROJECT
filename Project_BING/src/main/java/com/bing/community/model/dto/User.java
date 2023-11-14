package com.bing.community.model.dto;

public class User {
	private int id; // 쓸수도, 안쓸수도
	private String email;
	private String pw;
	private String name;
	private String gender;
	private String nickname;
	private int height;
	private int weight;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(int id, String email, String pw, String name, String gender, String nickname, int height,
			int weight) {
		super();
		this.id = id;
		this.email = email;
		this.pw = pw;
		this.name = name;
		this.gender = gender;
		this.nickname = nickname;
		this.height = height;
		this.weight = weight;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

}
