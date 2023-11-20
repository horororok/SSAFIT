package com.ssafy.ssafit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.Message;
import com.ssafy.ssafit.model.service.MessageService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	private MessageService mservice;
	
	
	
	
	
	
}
