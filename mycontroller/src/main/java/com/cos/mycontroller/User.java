package com.cos.mycontroller;

public class User {

	
	//1. username, password있네
	//2. setter실행
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		System.out.println("유저네임 실행됨");
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		System.out.println("패스워드 실행됨");
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	
	
}
