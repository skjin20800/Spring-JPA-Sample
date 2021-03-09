package com.cos.myiocid;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import lombok.Getter;

@Getter
// @Component //IoC
// @Configuration  //IoC
// @Service //IoC
// @Repository //IoC
public class Robot {
	
	private String name = "건담";

}
