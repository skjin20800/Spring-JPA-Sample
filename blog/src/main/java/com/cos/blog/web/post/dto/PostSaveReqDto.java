package com.cos.blog.web.post.dto;

import javax.validation.constraints.NotBlank;

import com.cos.blog.domain.post.Post;

import lombok.Data;

@Data
public class PostSaveReqDto {

	@NotBlank(message = "제목을 입력하세요")
	private String title;
	@NotBlank(message = "내용을 입력하세요")
	private String content;

	
	public Post toEntity() {
		return Post.builder()
				.title(title)
		        .content(content)
		        .build();
		}
	}
				
			
	