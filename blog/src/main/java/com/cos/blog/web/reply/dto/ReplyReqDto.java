package com.cos.blog.web.reply.dto;

import com.cos.blog.domain.reply.Reply;

import lombok.Data;

@Data
public class ReplyReqDto {

	private String replyContent;
	private String postId;
	
	public Reply toEntity() {
		return Reply.builder()
		        .content(replyContent)
		        .build();
		}
}
