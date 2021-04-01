package com.cos.costagram.web.dto.user;

import lombok.Data;

@Data
public class UserProfileUpdateReqDto {
	
	private String username;
	
	private String name; // 이름
	private String website; // 자기 홈페이지
	private String bio; // 자기소개
	private String email;
	private String phone;
	private String gender;

}
