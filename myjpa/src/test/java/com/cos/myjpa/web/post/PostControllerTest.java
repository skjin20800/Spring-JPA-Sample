package com.cos.myjpa.web.post;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import com.cos.myjpa.domain.post.PostRepository;
import com.cos.myjpa.domain.user.User;
import com.cos.myjpa.service.PostService;
import com.cos.myjpa.web.dto.CommonRespDto;
import com.cos.myjpa.web.post.dto.PostSaveReqDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class PostControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private  PostService postService;
	@Autowired
	private PostRepository postRepository;
	 //JPA는 EntityManager의 부연채
	@Autowired
	private EntityManager entityManager;
	
	@BeforeEach
	public void init() {
		entityManager.createNativeQuery("ALTER TABLE post ALTER COLUMN id RESTART WITH 1").executeUpdate();
	}
	

	//BDDMockito 패턴
	@Test
	public CommonRespDto<?> 글쓰기테스트() throws Exception {
		// given(테스트를 하기 위한 준비)
		
		
		User principal = new User(1L, "텟트","1234","sk@sk", LocalDateTime.now(),null);
		PostSaveReqDto postSaveReqDto = new PostSaveReqDto("제목","타이틀");
		String content = new ObjectMapper().writeValueAsString(postSaveReqDto);

		if(principal == null) {
			return null;
		}else {
			//when(테스트 실행) //ResultAction -> 응답을 받을수있음
			ResultActions resultAction = mockMvc.perform(post("/post") //get,put,post등
					.contentType(MediaType.APPLICATION_JSON_UTF8)//던지는타입,contentType("applicaton/json")
					.content(content)//실제던질데이터
					.accept(MediaType.APPLICATION_JSON_UTF8));//응답받을 데이터
			
			//then (검증)
			resultAction
			.andExpect(status().isCreated())//(status의 결과값을, isCreated로 기대한다)
			.andExpect(jsonPath("$.title").value("스프링 따라하기"))//jsonPath - json을 리턴한다.//
			.andDo(MockMvcResultHandlers.print()); //결과를 콘솔에 보여준다
	
	return new CommonRespDto<>(1, "성공",postService.글쓰기(postSaveReqDto, principal));
		}
	
	}
	
	}
	
	
