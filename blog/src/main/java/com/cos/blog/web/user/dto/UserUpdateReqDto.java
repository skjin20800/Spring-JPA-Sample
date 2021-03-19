package com.cos.blog.web.user.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserUpdateReqDto {
	
	@NotBlank(message = "이름을 입력하세요")
	private String username;
	
	
	@NotBlank(message = "비밀번호를 입력하세요")
	@NotNull(message = "비밀번호를 입력하세요")
	@Size(max = 20, message = "비밀번호 길이를 초과하였습니다")
	private String password;
	
	@NotBlank(message = "이메일을 입력하세요")
	private String email;
	
	//toEntity 안만드는 이유는 더티체킹을 할 것이기 때문!!
	
}
