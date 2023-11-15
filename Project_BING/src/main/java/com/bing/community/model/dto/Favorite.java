package com.bing.community.model.dto;

public class Favorite {
	private String user_email;
	private String favorite_type;
	private int favorite_id;
	private int location_id;
	private int board_id;

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getFavorite_type() {
		return favorite_type;
	}

	public void setFavorite_type(String favorite_type) {
		this.favorite_type = favorite_type;
	}

	public int getFavorite_id() {
		return favorite_id;
	}

	public void setFavorite_id(int favorite_id) {
		this.favorite_id = favorite_id;
	}

	public int getLocation_id() {
		return location_id;
	}

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public Favorite(String user_email, String favorite_type, int favorite_id, int location_id, int board_id) {
		super();
		this.user_email = user_email;
		this.favorite_type = favorite_type;
		this.favorite_id = favorite_id;
		this.location_id = location_id;
		this.board_id = board_id;
	}

	public Favorite() {
		// TODO Auto-generated constructor stub
	}
}
