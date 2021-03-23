package com.cos.costagram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration //IoC등록
@EnableWebSecurity //내가 커스텀한 시큐리티 사용하기
public class SecurityConfig extends WebSecurityConfigurerAdapter{
//	SecurityConfig타입이 될려면 WebSecurityConfigurerAdapter 선언
	
//	private final OAuth2DetailsService oAuth2DetailsService;
	
	//IoC등록만 하면 AuthenticationManager가 Bcrypt로 자동 검증해줌
	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}; 
	
	//모델 : Image, User, Likes, Follow, Tag : 인증 필요
	// auth 주소 : 인증 필요 없음.
	// static 폴더	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); //csrf비활성화
		http.authorizeRequests()
		.antMatchers("/","/user/**","/image/**","/follow/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") //user,post만 인증 및 허용//ROLE_는 강제성이 있음. 롤 검증시 사용
		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
		.anyRequest()
		.permitAll() //나머지 다 허용
		.and()
		.formLogin() //x-www-form-urlencoded
		.loginPage("/auth/loginForm") //로그인 주소
		.loginProcessingUrl("/login") //Spring Security가 Post방식으로  "/login" 주소가 들어오면 낚아챈다
		.defaultSuccessUrl("/");
		//OAuth2.0과 CORS는 나중에
		
		
	}
}
