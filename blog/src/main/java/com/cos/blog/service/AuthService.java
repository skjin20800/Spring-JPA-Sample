package com.cos.blog.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.domain.user.RoleType;
import com.cos.blog.domain.user.User;
import com.cos.blog.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional
	public void 회원가입(User user) {
		
		String rawPassword = user.getPassword(); //비밀번호를 받아온다
		String encPassword = bCryptPasswordEncoder.encode(rawPassword); //받아온 비밀번호를 해쉬암호화한다.
		
		user.setPassword(encPassword);// 암호화 암호 등록
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}

}
