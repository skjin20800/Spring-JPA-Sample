package com.cos.myjpa.web.post.dto;

import com.cos.myjpa.domain.post.Post;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostSaveReqDto {
	// 여기서 validation 체크
	private String title;
	private String content;
	
	public Post toEntity() {
		return Post.builder()
				.title(title)
				.content(content)
				.build();
	}
}
