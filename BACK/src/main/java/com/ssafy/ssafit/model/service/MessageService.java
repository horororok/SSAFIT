package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.Message;

public interface MessageService {
	
	//메시지 작성 (메시지 등록)
	public int writeMessage(Message message);
		
	//메시지 읽기 (메시지 하나 반환)
	public Message readMessage(int message_id);
	
	//읽지 않은 메시지 목록 반환
	public List<Message> getUnreadMessage(int user_to_id);
	
	//받은 메시지 목록 반환 (받은 메시지함)
	public List<Message> getReceivedMessage(int user_to_id);
	
	//보낸 메시지 목록 반환 (보낸 메시지함)
	public List<Message> getSentMessage(int user_from_id);
		
	//사용자가 보낸/받은 모든 메시지 목록 반환
	public List<Message> getAllMessage(int userId);
	
}
