package com.ssafy.ssafit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SsafitApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(SsafitApplication.class, args);
		
//		ApplicationContext context = SpringApplication.run(SsafitApplication.class, args);
//		for(String name : context.getBeanDefinitionNames()) {
//			System.out.println(name); //빈 등록 확인
//		}
	}

}
