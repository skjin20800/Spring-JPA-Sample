package com.cos.myjpa.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonRespDto<T> {
	private int statusCode; // 1정상, -1실패
	private String msg; // 오류 내용
	private T data;
}
