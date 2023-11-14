package com.bing.community.model.dto;

public class Reply {
	private int reply_id;
	private int board_id;
	private String writer;
	private String content;
	private String reg_date;
	private String is_modified;

	public Reply() {
		// TODO Auto-generated constructor stub
	}

	public Reply(int reply_id, int board_id, String writer, String content, String reg_date, String is_modified) {
		super();
		this.reply_id = reply_id;
		this.board_id = board_id;
		this.writer = writer;
		this.content = content;
		this.reg_date = reg_date;
		this.is_modified = is_modified;
	}

	public int getReply_id() {
		return reply_id;
	}

	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
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

	public String getIs_modified() {
		return is_modified;
	}

	public void setIs_modified(String is_modified) {
		this.is_modified = is_modified;
	}

}
