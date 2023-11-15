package com.bing.community.model.dto;

public class SearchCondition {
	
	//정렬은 검색 후 vue에서 구현
	private String key = "none";
	private String word;
	
	public SearchCondition() {}

	public SearchCondition(String key, String word) {
		super();
		this.key = key;
		this.word = word;
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
	
	
}
