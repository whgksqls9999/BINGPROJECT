package com.bing.community.model.dto;

public class Board {
	private int board_id;
	private int community_id;
	private int num;
	private int view_cnt;
	private String header;
	private String title;
	private String writer;
	private String content;
	private String reg_date;
	private String is_modified;
	private int location_id;
	private int fav_cnt;

	
	public Board() {}


	public Board(int board_id, int community_id, int num, int view_cnt, String header, String title, String writer,
			String content, String reg_date, String is_modified, int location_id, int fav_cnt) {
		super();
		this.board_id = board_id;
		this.community_id = community_id;
		this.num = num;
		this.view_cnt = view_cnt;
		this.header = header;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.reg_date = reg_date;
		this.is_modified = is_modified;
		this.location_id = location_id;
		this.fav_cnt = fav_cnt;
	}


	public int getBoard_id() {
		return board_id;
	}


	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}


	public int getCommunity_id() {
		return community_id;
	}


	public void setCommunity_id(int community_id) {
		this.community_id = community_id;
	}


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public int getView_cnt() {
		return view_cnt;
	}


	public void setView_cnt(int view_cnt) {
		this.view_cnt = view_cnt;
	}


	public String getHeader() {
		return header;
	}


	public void setHeader(String header) {
		this.header = header;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
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


	public int getLocation_id() {
		return location_id;
	}


	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}


	public int getFav_cnt() {
		return fav_cnt;
	}


	public void setFav_cnt(int fav_cnt) {
		this.fav_cnt = fav_cnt;
	}


	@Override
	public String toString() {
		return "Board [board_id=" + board_id + ", community_id=" + community_id + ", num=" + num + ", view_cnt="
				+ view_cnt + ", header=" + header + ", title=" + title + ", writer=" + writer + ", content=" + content
				+ ", reg_date=" + reg_date + ", is_modified=" + is_modified + ", location_id=" + location_id
				+ ", fav_cnt=" + fav_cnt + "]";
	}
	
	
}
