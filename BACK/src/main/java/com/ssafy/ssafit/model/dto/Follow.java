package com.ssafy.ssafit.model.dto;

public class Follow {
	private int user_from_id;
	private int user_to_id;
	
	public Follow() {}

	public Follow(int user_from_id, int user_to_id) {
		this.user_from_id = user_from_id;
		this.user_to_id = user_to_id;
	}

	public int getUser_from_id() {
		return user_from_id;
	}

	public void setUser_from_id(int user_from_id) {
		this.user_from_id = user_from_id;
	}

	public int getUser_to_id() {
		return user_to_id;
	}

	public void setUser_to_id(int user_to_id) {
		this.user_to_id = user_to_id;
	}

	@Override
	public String toString() {
		return "Follow [user_from_id=" + user_from_id + ", user_to_id=" + user_to_id + "]";
	}

}
