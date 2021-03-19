package com.cos.blog.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.domain.post.Post;
import com.cos.blog.domain.post.PostRepository;
import com.cos.blog.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin
@RestController
public class ReplyControllerTest {
	
	private final PostRepository postRepository;
	
	// 게시글 상세보기(user.post.reply들)
	
	@GetMapping("/test/post/{id}")
	public CMRespDto<?> updateForm(@PathVariable int id) {
		Post postEntity= postRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("id를 찾을 수 없습니다.");	
		});
		
		return new CMRespDto<>(1,postEntity);	
	}


}
