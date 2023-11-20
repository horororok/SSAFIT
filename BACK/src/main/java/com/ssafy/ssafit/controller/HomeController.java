package com.ssafy.ssafit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.News;
import com.ssafy.ssafit.model.service.HomeService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private HomeService hservice;
	
	@GetMapping("news")
	public ResponseEntity<?> getNews(){
		List<News> news = hservice.getNewsList();
		
		if(news == null || news.size() < 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<News>>(news, HttpStatus.OK);
	}
	
	
}
