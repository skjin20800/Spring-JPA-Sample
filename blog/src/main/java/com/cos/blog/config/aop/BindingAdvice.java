package com.cos.blog.config.aop;


import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.cos.blog.web.dto.CMRespDto;

// @Controller, @RestController, @Component, @Configuration //메모리를 띄우는법
// @Configuration : 설정파일, 최초에 메모리에 뜸
// @Component : 필요할때만 메모리에 뜸
// @Aspect : 공통기능

@Component
@Aspect
public class BindingAdvice {

	private static final Logger log = LoggerFactory.getLogger(BindingAdvice.class);
	//@Before
	//@After
	@Around("execution(* com.cos.blog.web..*Controller.*(..))")
	public Object validCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		System.out.println("AOP실행");
		String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
		String method = proceedingJoinPoint.getSignature().getName();
		System.out.println("type : "+type);
		System.out.println("method : "+method);
		Object[] args = proceedingJoinPoint.getArgs();
		
		
		for (Object arg : args) {
			
			if(arg instanceof BindingResult) {
				
				BindingResult bindingResult = (BindingResult) arg;
				System.out.println("냐미 "+ bindingResult.toString());
				// 서비스 : 정상적인 화면 -> 사용자요청
				if(bindingResult.hasErrors()) {
					Map<String, String> errorMap = new HashMap<>();
					for(FieldError error : bindingResult.getFieldErrors()) {
						errorMap.put(error.getField(), error.getDefaultMessage());
						log.warn(type+"."+method+"() => 필드 : "+error.getField()+", 메시지 : "+error.getDefaultMessage());
						log.debug(type+"."+method+"() => 필드 : "+error.getField()+", 메시지 : "+error.getDefaultMessage());
					}
					return new CMRespDto<>(HttpStatus.BAD_REQUEST.value(), errorMap);
				}
			}
		}
		return proceedingJoinPoint.proceed(); // 함수의 스택을 실행해라
	}            
}
