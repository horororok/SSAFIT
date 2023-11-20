package com.ssafy.ssafit.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.MessageDao;

@Service
public class MessageServiceImpl implements MessageService {
	
	private MessageDao messageDao;
	
	@Autowired
	public MessageServiceImpl(MessageDao messageDao) {
		this.messageDao = messageDao;
	}
	
	
	
	
}
