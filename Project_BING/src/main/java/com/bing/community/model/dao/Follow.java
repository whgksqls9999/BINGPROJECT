package com.bing.community.model.dao;

public class Follow {
	private int follow_id;
	private String follow_from;
	private String follow_to;

	public Follow() {
		// TODO Auto-generated constructor stub
	}

	public Follow(int follow_id, String follow_from, String follow_to) {
		super();
		this.follow_id = follow_id;
		this.follow_from = follow_from;
		this.follow_to = follow_to;
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

}
