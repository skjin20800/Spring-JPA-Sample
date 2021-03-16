package com.cos.blog.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetails;
import com.cos.blog.domain.reply.Reply;
import com.cos.blog.service.ReplyService;
import com.cos.blog.web.dto.CMRespDto;
import com.cos.blog.web.reply.dto.ReplyReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ReplyController {
	
	private final ReplyService replyService;
	
	@DeleteMapping("/reply/{id}")
	public CMRespDto<?> deleteById(@PathVariable int id,
			@AuthenticationPrincipal PrincipalDetails principalDetails){
		
		//모든 컨트롤러에 삭제하기, 수정하기는 무조건 동일 인물이 로그인 했는지 확인!!!
		int result = replyService.삭제하기(id, principalDetails.getUser().getId());
		return new CMRespDto<>(result,null);
	}
	
	@PostMapping("/reply")
	public CMRespDto<?> save(@RequestBody ReplyReqDto replyReqDto,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		System.out.println("실행됨"+ replyReqDto);
		
		Reply reply = replyReqDto.toEntity();
		reply.setUser(principalDetails.getUser());
		int result = replyService.댓글쓰기(reply, replyReqDto);
		
		return new CMRespDto<>(result,null);
	}

}
