package com.cos.jwtex1.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.jwtex1.domain.User;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class PrincipalDetails implements UserDetails {

	private User user;

	public PrincipalDetails(User user) {
		this.user = user;
	}
	@Override
	public String getPassword() {
		return user.getPassword();
	}
	@Override
	public String getUsername() {
		return user.getUsername();
	}
	@Override
	public boolean isAccountNonExpired() { //인증 만료기한 ex 1년 등등
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() { //몇번 실패시 막는것 
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { // 비밀번호 만료
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() { //계정 활성화
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.println("롤 검증 하는 중");
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(()->"ROLE_"+user.getRole().toString());
		return collectors;		
	}
}
