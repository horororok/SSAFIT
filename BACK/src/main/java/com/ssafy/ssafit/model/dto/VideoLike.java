package com.ssafy.ssafit.model.dto;

public class VideoLike {
	private int user_id;
	private int video_id;
	
	public VideoLike() {
	}

	public VideoLike(int user_id, int video_id) {
		this.user_id = user_id;
		this.video_id = video_id;
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
		return "VideoLike [user_id=" + user_id + ", video_id=" + video_id + "]";
	}
	
}
