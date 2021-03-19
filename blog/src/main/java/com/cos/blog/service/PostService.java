package com.cos.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.domain.post.Post;
import com.cos.blog.domain.post.PostRepository;
import com.cos.blog.web.post.dto.PostSaveReqDto;
import com.cos.blog.web.post.dto.PostSearchReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {

	private final PostRepository postRepository;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<Post> 검색하기(PostSearchReqDto postSearchReqDto, Pageable pageable) {
		return postRepository.findByTitleContaining(postSearchReqDto.getKeyword(), pageable);
	}

	@Transactional(readOnly = true)
	public Page<Post> 전체찾기(Pageable pageable) {
		return postRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Post 상세보기(int id) {
		return postRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});
	}

	@Transactional
	public Post 글쓰기(Post post) {
		return postRepository.save(post);
	}

	@Transactional
	public Post 글수정(int id, PostSaveReqDto postSaveReqDto) {
		Post postEntity = postRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});
		postEntity.setTitle(postSaveReqDto.getTitle());
		postEntity.setContent(postSaveReqDto.getContent());
		return postEntity;
	}

	@Transactional
	public void 삭제하기(int id) {
		postRepository.deleteById(id);
	}

}
