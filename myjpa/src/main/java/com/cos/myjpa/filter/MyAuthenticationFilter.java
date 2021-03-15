package com.cos.myjpa.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cos.myjpa.domain.user.User;
import com.cos.myjpa.handler.ex.MyAuthenticationException;

public class MyAuthenticationFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		User principal = (User) session.getAttribute("principal");
		
		if(principal == null) {
			throw new MyAuthenticationException();
			
		}else {
			chain.doFilter(request, response);
		}
	}
}
