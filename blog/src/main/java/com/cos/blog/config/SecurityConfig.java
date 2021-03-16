package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.oauth.OAuth2DetailsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration //IoC등록
@EnableWebSecurity //내가 커스텀한 시큐리티 사용하기
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	//SecurityConfig타입이 될려면 WebSecurityConfigurerAdapter 선언
	
	private final OAuth2DetailsService oAuth2DetailsService;
	
	//IoC등록만 하면 AuthenticationManager가 Bcrypt로 자동 검증해줌
	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}; 
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); //csrf비활성화
		http.authorizeRequests()
		.antMatchers("/user/**","/post/**","/reply/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") //user,post만 인증 및 허용//ROLE_는 강제성이 있음. 롤 검증시 사용
		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
		.anyRequest().permitAll() //나머지 다 허용
		.and()
		.formLogin() //x-www-form-urlencoded
		.loginPage("/loginForm") //로그인 주소
		.loginProcessingUrl("/login") //Spring Security가 Post방식으로  "/login" 주소가 들어오면 낚아챈다
		.defaultSuccessUrl("/") //로그인이 끝나고 보여줄 기본 주소
		.and()
		.oauth2Login() //oauth 기본문법
		.userInfoEndpoint() //oauth 기본문법
		.userService(oAuth2DetailsService); //우리가 커스텀해야할곳
	}
}
