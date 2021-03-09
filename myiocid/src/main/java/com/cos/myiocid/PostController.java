package com.cos.myiocid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// IOC에 띄우는방법 5가지
// Component(용도없음), Configuration(설정파일), Service(서비스), Repository(레파지토리), Bean

// IoC는 싱글톤으로 같은이름의 클래스는 하나만존재
// RestController, Controller -> IoC(싱글톤) 등록 new PostController(주입);
@RestController
public class PostController {
	
	@Autowired
	private Robot robot; //DI
		
	public PostController(Robot robot) {
		super();
		this.robot = robot;
	}

	@GetMapping("/")
	public String home() {
		return "home" + robot.getName();
	}

}
