package com.cos.blog.web.post.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PostSearchReqDto {
	
	@NotNull
	@NotBlank(message = "검색어를 입력하세요")
	private String keyword; 

}
