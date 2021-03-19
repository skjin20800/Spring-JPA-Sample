package com.cos.jwtex1.config.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cos.jwtex1.config.auth.PrincipalDetails;
import com.cos.jwtex1.domain.User;
import com.cos.jwtex1.domain.UserRepository;

public class JwtVerifyFilter extends BasicAuthenticationFilter {

	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	
		
	public JwtVerifyFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
		super(authenticationManager);
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		
	} 

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String header = request.getHeader("Authorization");
		System.out.println(header);
		
		//토큰을 안들고왔을때의 if문
		if(header == null || !header.startsWith("Bearer")) {
			chain.doFilter(request, response);
			return;
		}
		
		String token = request.getHeader("Authorization").replace("Bearer", "");
		
		// 검증1 (헤더+페이로드+시크릿을 HMAC512 해쉬한 값) == SIGNATURE
		// 검증2 (만료시간 확인)
		
		DecodedJWT dJwt = JWT.require(Algorithm.HMAC512("홍길동")).build().verify(token);
		Long userId = dJwt.getClaim("userId").asLong();
		
		User userEntity = userRepository.findById(userId).orElseThrow(()->{
			return new IllegalArgumentException("id를 찾을 수 없습니다.");	
		});
		PrincipalDetails principalDetails = new PrincipalDetails(userEntity);
		
		
		Authentication authentication = 
				new UsernamePasswordAuthenticationToken(principalDetails.getUsername(), principalDetails.getPassword(),
						principalDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}
}
