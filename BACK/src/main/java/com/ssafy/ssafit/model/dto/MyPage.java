package com.ssafy.ssafit.model.dto;

public class MyPage {
	private int age;
	private int gender;
	private String self_intro;
	private String address;
	private String fav_sport;
	private String profile_image;
	private int user_id;
	
	public MyPage() {
	}

	public MyPage(int age, int gender, String self_intro, String address, String fav_sport, String profile_image,
			int user_id) {
		this.age = age;
		this.gender = gender;
		this.self_intro = self_intro;
		this.address = address;
		this.fav_sport = fav_sport;
		this.profile_image = profile_image;
		this.user_id = user_id;
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

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public String getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}

	@Override
	public String toString() {
		return "MyPage [age=" + age + ", gender=" + gender + ", self_intro=" + self_intro + ", address=" + address
				+ ", fav_sport=" + fav_sport + ", user_id=" + user_id + "]";
	}

}
