package com.cos.blog.web.auth.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.cos.blog.domain.user.User;

import lombok.Data;

// Valid 나중에 처리하자.

@Data
public class AuthJoinReqDto {
	
	
	@NotBlank(message = "아이디를 입력하세요")
	@Size(max = 20, message = "아이디 길이를 초과하였습니다")
	private String username;	
	
	@NotBlank(message = "비밀번호가 없습니다.")
	private String password;
	
	@NotBlank(message = "이메일이 없습니다.")
	private String email;
	
	public User toEntity() {
		return User.builder()
				.username(username)
				.password(password)
				.email(email)
				.build();
	}
	
}
