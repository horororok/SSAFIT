package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.MessageDao;
import com.ssafy.ssafit.model.dto.Message;

@Service
public class MessageServiceImpl implements MessageService {
	
	private MessageDao messageDao;
	
	@Autowired
	public MessageServiceImpl(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

	@Override
	public int writeMessage(Message message) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Message readMessage(int message_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> getUnreadMessage(int user_to_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> getReceivedMessage(int user_to_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> getSentMessage(int user_from_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> getAllMessage(int userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
