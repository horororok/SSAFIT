package com.ssafy.ssafit.model.dto;

//유저 기본 정보와 추가 정보 합친 dto (유저게시판에서 보여줄 유저 정보 바구니)
public class UserBoard {
	private int user_id;
	private String nickname;
	private int age;
	private int gender;
	private String profile_url;
	private String self_intro;
	private String dong_address;
	private String fav_sport;
	
	public UserBoard() {
	}

	public UserBoard(int user_id, String nickname, int age, int gender, String profile_url, String self_intro,
			String dong_address, String fav_sport) {
		this.user_id = user_id;
		this.nickname = nickname;
		this.age = age;
		this.gender = gender;
		this.profile_url = profile_url;
		this.self_intro = self_intro;
		this.dong_address = dong_address;
		this.fav_sport = fav_sport;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getProfile_url() {
		return profile_url;
	}

	public void setProfile_url(String profile_url) {
		this.profile_url = profile_url;
	}

	public String getSelf_intro() {
		return self_intro;
	}

	public void setSelf_intro(String self_intro) {
		this.self_intro = self_intro;
	}

	public String getDong_address() {
		return dong_address;
	}

	public void setDong_address(String dong_address) {
		this.dong_address = dong_address;
	}

	public String getFav_sport() {
		return fav_sport;
	}

	public void setFav_sport(String fav_sport) {
		this.fav_sport = fav_sport;
	}
	
}
