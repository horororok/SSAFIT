package com.ssafy.ssafit.model.dto;

public class Video {
	private int video_id;
	private String title;
	private String url;
	private String thumbnail;
	private String channel_name;
	private int view_cnt;
	private String part;
	private int liked_cnt; //좋아요 받은 개수 (모든 유저에게)
	private int is_user_liked; //현재 로그인 한 유저가 이 동영상을 좋아요 했는지 안했는지 (했으면 1, 아니면 0)
	
	public Video() {}

	public Video(int video_id, String title, String url, String thumbnail, String channel_name, int view_cnt,
			String part, int liked_cnt, int is_user_liked) {
		this.video_id = video_id;
		this.title = title;
		this.url = url;
		this.thumbnail = thumbnail;
		this.channel_name = channel_name;
		this.view_cnt = view_cnt;
		this.part = part;
		this.liked_cnt = liked_cnt;
		this.is_user_liked = is_user_liked;
	}

	public int getVideo_id() {
		return video_id;
	}

	public void setVideo_id(int video_id) {
		this.video_id = video_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}

	public int getView_cnt() {
		return view_cnt;
	}

	public void setView_cnt(int view_cnt) {
		this.view_cnt = view_cnt;
	}

	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}
	

	public int getLiked_cnt() {
		return liked_cnt;
	}

	public void setLiked_cnt(int liked_cnt) {
		this.liked_cnt = liked_cnt;
	}

	public int getIs_user_liked() {
		return is_user_liked;
	}

	public void setIs_user_liked(int is_user_liked) {
		this.is_user_liked = is_user_liked;
	}

	@Override
	public String toString() {
		return "Video [video_id=" + video_id + ", title=" + title + ", url=" + url + ", thumbnail=" + thumbnail
				+ ", channel_name=" + channel_name + ", view_cnt=" + view_cnt + ", part=" + part + ", liked_cnt="
				+ liked_cnt + ", is_user_liked=" + is_user_liked + "]";
	}

}
