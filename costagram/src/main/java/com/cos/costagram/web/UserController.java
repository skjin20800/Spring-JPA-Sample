package com.cos.costagram.web;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.costagram.config.auth.PrincipalDetails;
import com.cos.costagram.domain.user.User;
import com.cos.costagram.service.FollowService;
import com.cos.costagram.service.UserService;
import com.cos.costagram.web.dto.CMRespDto;
import com.cos.costagram.web.dto.follow.FollowRespDto;
import com.cos.costagram.web.dto.user.UserProfileRespDto;
import com.cos.costagram.web.dto.user.UserProfileUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {
	
	private final UserService userService;
	private final FollowService followService;
	
	@GetMapping("/user/{pageUserId}/follow")//data 리턴하는 것
	@ResponseBody
	public CMRespDto<?> followList(@PathVariable int pageUserId, @AuthenticationPrincipal PrincipalDetails principalDetails){
		
		List<FollowRespDto> follows = followService.팔로우리스트(principalDetails.getUser().getId(), pageUserId);
		return new CMRespDto<>(1,follows);
	}	
	

	@GetMapping("/user/{id}")
	public String profile(@PathVariable int id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		UserProfileRespDto userProfileRespDto = userService.회원프로필(id, principalDetails.getUser().getId());
		model.addAttribute("dto", userProfileRespDto);
		return "user/profile";
	}
	
	@GetMapping("/user/{id}/profileSetting")
	public String profileSetting(Model model,@PathVariable int id,  @AuthenticationPrincipal PrincipalDetails principalDetails) {
		UserProfileRespDto userProfileRespDto = userService.회원프로필(id, principalDetails.getUser().getId());
		model.addAttribute("user", userProfileRespDto.getUser());
		return "user/profileSetting";
	}
	
	@PutMapping("/user/profileUpdate")
	@ResponseBody
	public CMRespDto<?> profileUpdate(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails,@RequestBody UserProfileUpdateReqDto userProfileUpdateReqDto) {
		User user = userService.수정하기(principalDetails.getUser().getId(),userProfileUpdateReqDto );
		model.addAttribute("user", user);
		return new CMRespDto<>(1,null);	
	}
}
