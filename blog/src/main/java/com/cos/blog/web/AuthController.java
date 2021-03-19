package com.cos.blog.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.blog.service.AuthService;
import com.cos.blog.web.auth.dto.AuthJoinReqDto;

import lombok.RequiredArgsConstructor;

// 로그인, 로그아웃, 회원가입, 회원정보 변경

@RequiredArgsConstructor
@Controller
public class AuthController {
	
	private final AuthService authService;
	
	// 주소 : 인증안되었을 때 /user, /post, 인증이 되던 말던 무조건 온다./loginForm
	@GetMapping("/loginForm")
	public String login() {
		return "auth/loginForm";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "auth/joinForm";
	}
	
	@PostMapping("/join")
	public String join(@Valid AuthJoinReqDto authJoinReqDto, BindingResult bindingResult) {
		authService.회원가입(authJoinReqDto.toEntity());
		return "redirect:/loginForm"; // 로그인 로직 다시 때리기
	}
	
}
