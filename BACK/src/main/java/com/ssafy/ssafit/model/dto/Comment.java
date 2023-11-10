package com.ssafy.ssafit.model.dto;

public class Comment {
	
	private int comment_id;
	private String content;
	private String created_at;
	private int user_id;
	private int sportsboard_id;
	
	public Comment() {	
	}

	public Comment(int comment_id, String content, String created_at, int user_id, int sportsboard_id) {
		this.comment_id = comment_id;
		this.content = content;
		this.created_at = created_at;
		this.user_id = user_id;
		this.sportsboard_id = sportsboard_id;
	}

	public int getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
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

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getSportsboard_id() {
		return sportsboard_id;
	}

	public void setSportsboard_id(int sportsboard_id) {
		this.sportsboard_id = sportsboard_id;
	}

	@Override
	public String toString() {
		return "Comment [comment_id=" + comment_id + ", content=" + content + ", created_at=" + created_at
				+ ", user_id=" + user_id + ", sportsboard_id=" + sportsboard_id + "]";
	}

}
