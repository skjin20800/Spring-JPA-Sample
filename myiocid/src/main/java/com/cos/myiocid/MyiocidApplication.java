package com.cos.myiocid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyiocidApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyiocidApplication.class, args);
	}

	
	@Bean //실행시에 IoC에 등록 (리턴값을 IoC 컨테이너에 저장함)
	public Robot getRobet() {
		return new Robot();
	}
}
