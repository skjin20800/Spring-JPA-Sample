package com.cos.costagram.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.costagram.domain.follow.FollowRepository;
import com.cos.costagram.domain.user.User;
import com.cos.costagram.domain.user.UserRepository;
import com.cos.costagram.web.dto.user.UserProfileRespDto;
import com.cos.costagram.web.dto.user.UserProfileUpdateReqDto;

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
				userEntity.getImages().forEach((image)->{
					image.setLikeCount(image.getLikes().size());
				});
				userProfileRespDto.setUser(userEntity);
				
				return userProfileRespDto;
	}
	
	@Transactional
	public User 수정하기(int id, UserProfileUpdateReqDto userProfileUpdateReqDto){
		System.out.println("디티오"+ userProfileUpdateReqDto);
		// 영속화
		User userEntity = userRepository.findById(id).orElseThrow(()-> {
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});
		
		userEntity.setUsername(userProfileUpdateReqDto.getUsername());
		userEntity.setName(userProfileUpdateReqDto.getName());
		userEntity.setWebsite(userProfileUpdateReqDto.getWebsite());
		userEntity.setBio(userProfileUpdateReqDto.getBio());
		userEntity.setEmail(userProfileUpdateReqDto.getEmail());
		userEntity.setPhone(userProfileUpdateReqDto.getPhone());
		userEntity.setGender(userProfileUpdateReqDto.getGender());
			
		return userEntity;
	} // 서비스 종료시 영속성 컨텍스트에 영속화 되어있는 모든 객체의 변경을 감지하여 변경된 아이들을 flush 한다. (commit) = 더티체킹
	

}
