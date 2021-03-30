package com.cos.costagram.web.dto.follow;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowRespDto {
	private int userId;
	private String username;
	private String profileImageUrl;
	private BigInteger followState;//boolean 값은 BigInteger로 받아야함.
	private BigInteger equalState;

}
