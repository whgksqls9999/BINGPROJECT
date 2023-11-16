package com.bing.community.model.dto;

public class FavoriteBoard {

	private int favorite_boardId;
	private int board_id;
	private String writername;
	private String title;
	private String content;
	private String reg_date;

	public FavoriteBoard() {
		// TODO Auto-generated constructor stub
	}

	public FavoriteBoard(int favorite_boardId, int board_id, String writername, String title, String content,
			String reg_date) {
		super();
		this.favorite_boardId = favorite_boardId;
		this.board_id = board_id;
		this.writername = writername;
		this.title = title;
		this.content = content;
		this.reg_date = reg_date;
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

	public String getWritername() {
		return writername;
	}

	public void setWritername(String writername) {
		this.writername = writername;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

}
