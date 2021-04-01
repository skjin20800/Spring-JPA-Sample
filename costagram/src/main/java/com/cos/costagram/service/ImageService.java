package com.cos.costagram.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.costagram.config.auth.PrincipalDetails;
import com.cos.costagram.domain.image.Image;
import com.cos.costagram.domain.image.ImageRepository;
import com.cos.costagram.domain.tag.Tag;
import com.cos.costagram.domain.tag.TagRepository;
import com.cos.costagram.utils.TagUtils;
import com.cos.costagram.web.dto.image.ImageReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {

	private final ImageRepository imageRepository;
	private final TagRepository tagRepository;
	@Value("${file.path}") //yml에 적어놓은 file.path의 경로 가져오기
	private String uploadFolder;
	
	
	public List<Image> 피드이미지(int principalId){
		
		// 1. principalId 로 내가 팔로우하고 있는 사용자를 찾아야 됨. (한개이거나 컬렉션이거나)
		// select * from image where userId in (select toUserId from follow where fromUserId = 1);
		List<Image> images = imageRepository.mFeed(principalId);
		
		images.forEach((image)->{
			
			int likeCount = image.getLikes().size();				
			image.setLikeCount(likeCount);
			
		image.getLikes().forEach((like)->{
			if(like.getUser().getId()==principalId) {
				image.setLikeState(true);
			}
		});
		
		});
		
		return images;
	}
	
	
	@Transactional
	public void 사진업로드(ImageReqDto imageReqDto, PrincipalDetails principalDetails) {
		
		UUID uuid = UUID.randomUUID(); //파일명 랜덤함수로 겹치치않게 설정
		String imageFileName= uuid+"_"+imageReqDto.getFile().getOriginalFilename(); //메모리에 저장
		System.out.println("파일명 : "+imageFileName);
		
		Path imageFilePath = Paths.get(uploadFolder+imageFileName); //저장경로( 쓰기)
		System.out.println("파일패스 : "+imageFilePath);
		try {
			Files.write(imageFilePath, imageReqDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//참고 : 엔티티에 Tag는 주인이 아니다. Image 엔티티를 통해서 Tag를 save를 할 수 있다.
		
		System.out.println("이미지파일 유알엘"+imageFileName);
		//1, Image 저장
		Image image = imageReqDto.toEntity(imageFileName, principalDetails.getUser());
		Image imageEntity = imageRepository.save(image);
		
		//2. Tag 저장
		List<Tag> tags = TagUtils.parsing(imageReqDto.getTags(), imageEntity);
		tagRepository.saveAll(tags);
		
		
	}
	
}
