package com.bing.community.model.dto;

public class FavoriteBoard {

	private int favorite_boardId;
	private int board_id;
	private String user_email;

	public FavoriteBoard() {
	}

	public FavoriteBoard(int favorite_boardId, int board_id, String user_email) {
		super();
		this.favorite_boardId = favorite_boardId;
		this.board_id = board_id;
		this.user_email = user_email;
	}

	public int getFavorite_boardId() {
		return favorite_boardId;
	}

	public void setFavorite_boardId(int favorite_boardId) {
		this.favorite_boardId = favorite_boardId;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	
	
}
