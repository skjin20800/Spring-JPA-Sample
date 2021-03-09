package com.cos.myjpa.domain.user;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.myjpa.domain.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity // 서버 실행시에 테이블이 h2에 생성이 됨
@Builder
public class User { //User 1 <-> Post N
	@Id // PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Table, auto_increment, Sequence
	private Long id;
	private String username;
	private String password;
	private String email;
	
	@CreationTimestamp // 자동으로 현재시간이 들어감.
	private LocalDateTime createDate;
	
	// 역방향 매핑
	@JsonIgnoreProperties({"user"}) //Post에갔을때 user가나오면 무시한다.
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY) //나는 FK의 주인이 아니다.FK는 user변수명이다.
	private List<Post> post;
	
//	@javax.persistence.Transient
//	private int value;
	
}
