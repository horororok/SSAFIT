package com.ssafy.ssafit.model.dto;

public class Message {
	private int message_id;
	private int user_from_id;
	private int user_to_id;
	private String title;
	private String content;
	private String sent_at;
	private int is_read;
	
	public Message() {
	}

	public Message(int message_id, int user_from_id, int user_to_id, String title, String content, String sent_at,
			int is_read) {
		this.message_id = message_id;
		this.user_from_id = user_from_id;
		this.user_to_id = user_to_id;
		this.title = title;
		this.content = content;
		this.sent_at = sent_at;
		this.is_read = is_read;
	}

	public int getMessage_id() {
		return message_id;
	}

	public void setMessage_id(int message_id) {
		this.message_id = message_id;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSent_at() {
		return sent_at;
	}

	public void setSent_at(String sent_at) {
		this.sent_at = sent_at;
	}

	public int getIs_read() {
		return is_read;
	}

	public void setIs_read(int is_read) {
		this.is_read = is_read;
	}

	@Override
	public String toString() {
		return "Message [message_id=" + message_id + ", user_from_id=" + user_from_id + ", user_to_id=" + user_to_id
				+ ", title=" + title + ", content=" + content + ", sent_at=" + sent_at + ", is_read=" + is_read + "]";
	}
	
	
}
