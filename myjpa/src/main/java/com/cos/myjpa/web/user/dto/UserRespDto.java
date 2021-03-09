package com.cos.myjpa.web.user.dto;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.myjpa.domain.user.User;

import lombok.Data;

@Data
public class UserRespDto { //DTO 통신할때 필요한 오브젝트

	private Long id;
	private String username;
	private String password;
	private String email;
	
	@CreationTimestamp // 자동으로 현재시간이 들어감.
	private LocalDateTime createDate;
	
	public UserRespDto(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.createDate = user.getCreateDate();
	}

}
