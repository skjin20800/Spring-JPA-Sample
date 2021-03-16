package com.cos.blog.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.blog.config.auth.PrincipalDetails;
import com.cos.blog.domain.user.User;
import com.cos.blog.service.UserService;
import com.cos.blog.web.dto.CMRespDto;
import com.cos.blog.web.user.dto.UserUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {
		
	private final UserService UserService;
	
	// /user/1 -> 유저정보 가져오기
	@GetMapping("/user/{id}")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("id",id);
		return "user/updateForm";
	}
	
	@PutMapping("/user/{id}")
	public @ResponseBody CMRespDto<?> updateForm(@AuthenticationPrincipal PrincipalDetails principalDetails,
			 @PathVariable int id, @RequestBody UserUpdateReqDto userUpdateReqDto) {
		System.out.println("회원정보 수정 실행");
		User userEntity = UserService.회원수정(id, userUpdateReqDto);
		
		//세션 변경
		//방법1
       principalDetails.setUser(userEntity);

		//방법2
//    UsernamePasswordToke -> Authentication 객체로 만들어서 -> 시큐리티 컨텍스트 홀더에 집어 넣으면됨
//		Authentication authentication = 
//				new UsernamePasswordAuthenticationToken(userEntity.getUsername(), userEntity.getPassword());
//		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new CMRespDto<>(1,null);		
		
	}
	
	@GetMapping("/user")
	public @ResponseBody String findAll(@AuthenticationPrincipal PrincipalDetails principalDetails ) {//@Controller + @ResponseBody = @RestController
		System.out.println(principalDetails.getUsername());
		return "User";
	}
		
}
