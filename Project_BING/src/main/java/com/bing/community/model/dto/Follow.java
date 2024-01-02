package com.bing.community.model.dto;

public class Follow {
	private int follow_id;
	private String follow_from;
	private String follow_to;
	private String nickname;
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Follow() {
		// TODO Auto-generated constructor stub
	}

	public Follow(int follow_id, String follow_from, String follow_to, String nickname, String email) {
		super();
		this.follow_id = follow_id;
		this.follow_from = follow_from;
		this.follow_to = follow_to;
		this.nickname = nickname;
		this.email = email;
	}

	public int getFollow_id() {
		return follow_id;
	}

	public void setFollow_id(int follow_id) {
		this.follow_id = follow_id;
	}

	public String getFollow_from() {
		return follow_from;
	}

	public void setFollow_from(String follow_from) {
		this.follow_from = follow_from;
	}

	public String getFollow_to() {
		return follow_to;
	}

	public void setFollow_to(String follow_to) {
		this.follow_to = follow_to;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
