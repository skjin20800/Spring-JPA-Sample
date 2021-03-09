package com.cos.phoneapp2.web.dto;

import com.cos.phoneapp2.domain.Phone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PhoneReqDto {
	private String name;
	private String tel;
	
	public Phone toEntity() {
		return Phone.builder()
				.tel(tel)
				.name(name)
				.build();
	}


	
}
