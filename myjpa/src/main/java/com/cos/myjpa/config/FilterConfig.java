package com.cos.myjpa.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cos.myjpa.filter.MyAuthenticationFilter;

//web.xml

@Configuration
public class FilterConfig {
	
	@Bean
	 public FilterRegistrationBean<MyAuthenticationFilter> authenticationFilterRegister() {
		
		FilterRegistrationBean<MyAuthenticationFilter> bean = //필터 보관함에 필터 등록완료
				new FilterRegistrationBean<>(new MyAuthenticationFilter()); // 필터보관함에 필터 종류 선택
		
		bean.addUrlPatterns("/test");
		bean.setOrder(0); //필터 제일먼저 실행
		return bean;
	}

}
