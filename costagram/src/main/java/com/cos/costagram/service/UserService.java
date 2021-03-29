package com.cos.costagram.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.costagram.domain.follow.FollowRepository;
import com.cos.costagram.domain.user.User;
import com.cos.costagram.domain.user.UserRepository;
import com.cos.costagram.web.dto.user.UserProfileRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final FollowRepository followRepository;
	
	@Transactional
	public UserProfileRespDto 회원프로필(int userId, int principalId) {
		UserProfileRespDto userProfileRespDto = new UserProfileRespDto();
		
		User userEntity = userRepository.findById(userId).orElseThrow(
				()->{return new IllegalArgumentException(); }); // id가 없을때 에러발생
				
		int followState = followRepository.mFolllowState(principalId, userId);
		int followCount = followRepository.mFolllowCount(userId);
		
		userProfileRespDto.setFollowState(followState == 1);
				userProfileRespDto.setFollowCount(followCount);
				userProfileRespDto.setImageCount(userEntity.getImages().size());
				userProfileRespDto.setUser(userEntity);
				
				return userProfileRespDto;
	}
}
