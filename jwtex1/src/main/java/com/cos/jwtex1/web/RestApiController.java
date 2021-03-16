package com.cos.jwtex1.web;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.jwtex1.domain.User;
import com.cos.jwtex1.domain.UserRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@CrossOrigin
@RestController
public class RestApiController {
	
	private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping({"","/"})
	public String home() {
		return "<h1>Home</h1>";
	}
	
	@GetMapping("/user")
	public String user() {
		return "<h1>User</h1>";
	}
		
	@PostMapping("/admin")
	public String admin() {
		return "<h1>UserPost</h1>";
	}
	
	@PostMapping("/join")
	public User save(@RequestBody User user) {
		String rawPassword = user.getPassword();
		String encPassword = passwordEncoder.encode(rawPassword);
		
		user.setRole("USER");
		user.setPassword(encPassword);
		return userRepository.save(user);
	}
	
	
}
