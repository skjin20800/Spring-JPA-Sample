package com.cos.phoneapp2.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.phoneapp2.domain.Phone;
import com.cos.phoneapp2.service.PhoneService;
import com.cos.phoneapp2.web.dto.CMRespDto;
import com.cos.phoneapp2.web.dto.PhoneReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PhoneController {

	private final PhoneService phoneService;
	
	@GetMapping("/phone")
	public CMRespDto<?> findAll() {
		return new CMRespDto<>(1, phoneService.전체보기());
	}
	
	@GetMapping("/phone/{id}")
	public CMRespDto<?> findById(@PathVariable Long id) {
		return new CMRespDto<>(1, phoneService.상세보기(id));
	}
	
	@DeleteMapping("/phone/{id}")
	public CMRespDto<?> delete(@PathVariable Long id) {
		phoneService.삭제하기(id);
		return new CMRespDto<>(1, null);
	}
	
	@PutMapping("/phone/{id}")
	public CMRespDto<?> update(@PathVariable Long id, @RequestBody Phone phone) {	
		return new CMRespDto<>(1, phoneService.수정하기(id, phone));
	}
	
	@PostMapping("/phone")
	public CMRespDto<?> save(@RequestBody PhoneReqDto phoneReqDto) {
		System.out.println("저장하기컨트롤"+phoneReqDto.toEntity());
		return new CMRespDto<>(1, phoneService.저장하기(phoneReqDto));
	}
}






