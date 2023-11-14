package com.ssafy.ssafit.model.dto;

public class Video {
	private int video_id;
	private String title;
	private String url;
	private String thumbnail;
	private String channel_name;
	private int view_cnt;
	private String part;
	
	public Video() {}

	public Video(int video_id, String title, String url, String thumbnail, String channel_name, int view_cnt,
			String part) {
		this.video_id = video_id;
		this.title = title;
		this.url = url;
		this.thumbnail = thumbnail;
		this.channel_name = channel_name;
		this.view_cnt = view_cnt;
		this.part = part;
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

	@Override
	public String toString() {
		return "Video [video_id=" + video_id + ", title=" + title + ", url=" + url + ", thumbnail=" + thumbnail
				+ ", channel_name=" + channel_name + ", view_cnt=" + view_cnt + ", part=" + part + "]";
	}
	
}
