package com.cos.jwtex1.config.jwt;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cos.jwtex1.config.auth.PrincipalDetails;
import com.cos.jwtex1.web.dto.LoginReqDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

// 토큰 만들어주기
@RequiredArgsConstructor
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
	
	private final AuthenticationManager authenticationManager;
	
	// 주소 : POST요청으로 /login 요청이오면 동작
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException { 
		System.out.println("로그인 요청 옴");
		
		ObjectMapper om = new ObjectMapper();
		LoginReqDto loginReqDto = null;
		
		try {
			// om.readValue(request의 버퍼, 변환할 Dto)
			loginReqDto = om.readValue(request.getInputStream(), LoginReqDto.class);
			
		} catch (Exception e) {
			e.printStackTrace(); // throw new 커스텀익셉션날리기
		}
		//1. UsernamePassword 토큰 만들기
		UsernamePasswordAuthenticationToken authToken =
				new UsernamePasswordAuthenticationToken(loginReqDto.getUsername(), loginReqDto.getPassword());
		
		//2. AuthenticationManager에게 토큰을 전달하면 -> 자동으로 UserDetailsService가 호출 => 응답 Authentication
		Authentication authentication = authenticationManager.authenticate(authToken);
		
		return authentication;
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
	// JWT 토큰 만들어서 응답
		System.out.println("로그인 완료되어서 세션 만들어짐. 이제 JWT 토큰 만들어서 response.header에 응답할 차례");
		PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
		
		//JWT 토큰은 보안 파일이 아님!! - 전자서명, (전화번호, 주민번호, 이런거 넣으면안됨)
		String jwtToken = JWT.create()
				.withSubject("blogToken") //토큰의 제목
				.withExpiresAt(new Date(System.currentTimeMillis()+ (1000 * 60 *  10))) //만료시간
				.withClaim("userId", principalDetails.getUser().getId()) //payload값을 넣는다. (민감정보x)
				.sign(Algorithm.HMAC512("홍길동"));

				System.out.println("jwtToken :"+jwtToken);
	}
	
}
