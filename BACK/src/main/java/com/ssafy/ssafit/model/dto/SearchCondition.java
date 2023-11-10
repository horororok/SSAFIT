package com.ssafy.ssafit.model.dto;

public class SearchCondition {
	private String key = "none"; //검색기준
	private String word; //검색 내용
	private String orderBy = "none"; //정렬 기준
 	private String orderByDir = "ASC"; //정렬방향 (오름차순 default)
 	
 	public SearchCondition() {
	}

	public SearchCondition(String key, String word, String orderBy, String orderByDir) {
		this.key = key;
		this.word = word;
		this.orderBy = orderBy;
		this.orderByDir = orderByDir;
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

	@Override
	public String toString() {
		return "SearchCondition [key=" + key + ", word=" + word + ", orderBy=" + orderBy + ", orderByDir=" + orderByDir
				+ "]";
	}
	
}
