package com.ssafy.ssafit.model.dto;

public class Board {
	private int sportsboard_id;
	private String title; //제목
	private String content; //내용
	private String created_at; //등록 날짜
	private int view_cnt; //조회수
	private int user_id; //작성자
	
	public Board() {}

	public Board(int sportsboard_id, String title, String content, String created_at, int view_cnt, int user_id) {
		this.sportsboard_id = sportsboard_id;
		this.title = title;
		this.content = content;
		this.created_at = created_at;
		this.view_cnt = view_cnt;
		this.user_id = user_id;
	}

	public int getSportsboard_id() {
		return sportsboard_id;
	}

	public void setSportsboard_id(int sportsboard_id) {
		this.sportsboard_id = sportsboard_id;
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

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public int getView_cnt() {
		return view_cnt;
	}

	public void setView_cnt(int view_cnt) {
		this.view_cnt = view_cnt;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Board [sportsboard_id=" + sportsboard_id + ", title=" + title + ", content=" + content + ", created_at="
				+ created_at + ", view_cnt=" + view_cnt + ", user_id=" + user_id + "]";
	}
	
}