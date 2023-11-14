package com.ssafy.ssafit.model.dto;

public class Review {
	private int review_id;
	private String content;
	private String created_at;
	private int user_id;
	private int video_id;
	
	public Review() {
	}

	public Review(int review_id, String content, String created_at, int user_id, int video_id) {
		this.review_id = review_id;
		this.content = content;
		this.created_at = created_at;
		this.user_id = user_id;
		this.video_id = video_id;
	}

	public int getReview_id() {
		return review_id;
	}

	public void setReview_id(int review_id) {
		this.review_id = review_id;
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

	public int getVideo_id() {
		return video_id;
	}

	public void setVideo_id(int video_id) {
		this.video_id = video_id;
	}

	@Override
	public String toString() {
		return "Review [review_id=" + review_id + ", content=" + content + ", created_at=" + created_at + ", user_id="
				+ user_id + ", video_id=" + video_id + "]";
	}

	
}
