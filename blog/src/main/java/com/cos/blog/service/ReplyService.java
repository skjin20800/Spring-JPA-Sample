package com.cos.blog.service;

import org.springframework.stereotype.Service;

import com.cos.blog.domain.post.PostRepository;
import com.cos.blog.domain.reply.Reply;
import com.cos.blog.domain.reply.ReplyRepository;
import com.cos.blog.web.reply.dto.ReplyReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReplyService {
	
	private final ReplyRepository replyRepository;
	private final PostRepository postRepository;
	
	public int 삭제하기(int id, int userId) {
		
		Reply replyEntity = replyRepository.findById(id).get();
		
		if(replyEntity.getUser().getId()==userId) {
			replyRepository.deleteById(id);
			return 1;
		}else {
			return -1;
		}		
	}
	
	public int 댓글쓰기(Reply reply,ReplyReqDto replyReqDto) {
		
		int postId = Integer.parseInt(replyReqDto.getPostId());
		reply.setPost(postRepository.findById(postId).get()); 
		
		Reply replyEntity = replyRepository.save(reply);
		
		if(replyEntity != null) {
			return 1;
		}else {
			return -1;
		}		
	}

}
