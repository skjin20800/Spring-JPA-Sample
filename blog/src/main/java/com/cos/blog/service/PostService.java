package com.cos.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.domain.post.Post;
import com.cos.blog.domain.post.PostRepository;
import com.cos.blog.domain.user.User;
import com.cos.blog.web.post.dto.PostSaveReqDto;
import com.cos.blog.web.post.dto.PostUpdateReqDto;
import com.cos.blog.web.user.dto.UserUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {

	
	private final PostRepository postRepository;
	
	@Transactional(readOnly = true)
	public Page<Post> 전체찾기(Pageable pageable) {
		return postRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public Post 상세보기(int id) {
		return postRepository.findById(id).get();
	}
	
	
	@Transactional
	public Post 글쓰기(Post post) {
		return postRepository.save(post);
	}
	
	@Transactional
	public Post 글수정(int id, PostSaveReqDto postSaveReqDto) {
		Post postEntity = postRepository.findById(id).get();
		postEntity.setTitle(postSaveReqDto.getTitle());
		postEntity.setContent(postSaveReqDto.getContent());
		return postEntity;
	}
	

	@Transactional
	public void 삭제하기(int id) {
		postRepository.deleteById(id);
	}
	
}
