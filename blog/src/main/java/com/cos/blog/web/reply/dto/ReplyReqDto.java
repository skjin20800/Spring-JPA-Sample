package com.cos.blog.web.reply.dto;

import javax.validation.constraints.NotBlank;

import com.cos.blog.domain.reply.Reply;

import lombok.Data;

@Data
public class ReplyReqDto {

	@NotBlank(message = "내용을 입력하세요")
	private String replyContent;
	private String postId;
	
	public Reply toEntity() {
		return Reply.builder()
		        .content(replyContent)
		        .build();
		}
}
