package com.ssafy.ssafit.model.dto;

public class User {
	private int user_id;
	private String id;
	private String password;
	private String name;
	private String email;
	private String phone_num;
	private String nickname;
	
	public User() {	}

	public User(int user_id, String id, String password, String name, String email, String phone_num, String nickname) {
		this.user_id = user_id;
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone_num = phone_num;
		this.nickname = nickname;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", id=" + id + ", password=" + password + ", name=" + name + ", email="
				+ email + ", phone_num=" + phone_num + ", nickname=" + nickname + "]";
	}

}