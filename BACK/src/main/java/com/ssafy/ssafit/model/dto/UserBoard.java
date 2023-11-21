package com.ssafy.ssafit.model.dto;

import java.util.List;

//유저 기본 정보와 추가 정보 합친 dto (유저게시판에서 보여줄 유저 정보 바구니)
public class UserBoard {
	private int user_id;
	private String nickname;
	private int age;
	private int gender;
	private String self_intro;
	private String address;
	private String fav_sport;
	private int user_follow_cnt;
	
	public UserBoard(int user_id, String nickname, int age, int gender, String self_intro, String address,
			String fav_sport, int user_follow_cnt) {
		super();
		this.user_id = user_id;
		this.nickname = nickname;
		this.age = age;
		this.gender = gender;
		this.self_intro = self_intro;
		this.address = address;
		this.fav_sport = fav_sport;
		this.user_follow_cnt = user_follow_cnt;
	}
	public UserBoard() {
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
	public String getSelf_intro() {
		return self_intro;
	}
	public void setSelf_intro(String self_intro) {
		this.self_intro = self_intro;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFav_sport() {
		return fav_sport;
	}
	public void setFav_sport(String fav_sport) {
		this.fav_sport = fav_sport;
	}

	
	public int getUser_follow_cnt() {
		return user_follow_cnt;
	}
	public void setUser_follow_cnt(int user_follow_cnt) {
		this.user_follow_cnt = user_follow_cnt;
	}
	@Override
	public String toString() {
		return "UserBoard [user_id=" + user_id + ", nickname=" + nickname + ", age=" + age + ", gender=" + gender
				+ ", self_intro=" + self_intro + ", address=" + address + ", fav_sport=" + fav_sport
				+ ", user_follow_cnt=" + user_follow_cnt + "]";
	}

}
