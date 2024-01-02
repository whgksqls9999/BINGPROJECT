package com.bing.community.model.dto;

public class SearchCondition {

	// 정렬은 검색 후 vue에서 구현
	private String key = "title";
	private String word = "";
	private String orderBy = "board_id";
	private String orderByDir = "ASC";
	private int community_id;

	public SearchCondition() {
	}

	public SearchCondition(String key, String word, String orderBy, String orderByDir, int community_id) {
		super();
		this.key = key;
		this.word = word;
		this.orderBy = orderBy;
		this.orderByDir = orderByDir;
		this.community_id = community_id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrderByDir() {
		return orderByDir;
	}

	public void setOrderByDir(String orderByDir) {
		this.orderByDir = orderByDir;
	}

	public int getCommunity_id() {
		return community_id;
	}

	public void setCommunity_id(int community_id) {
		this.community_id = community_id;
	}

	@Override
	public String toString() {
		return "SearchCondition [key=" + key + ", word=" + word + ", orderBy=" + orderBy + ", orderByDir=" + orderByDir
				+ ", community_id=" + community_id + "]";
	}

}
