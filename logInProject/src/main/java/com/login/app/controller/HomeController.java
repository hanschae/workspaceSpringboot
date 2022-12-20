package com.login.app.controller;

import java.nio.charset.Charset;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.login.app.service.MemberService;
import com.login.app.vo.MemberVO;

@Controller
public class HomeController {
	@Inject
	MemberService service;
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@PostMapping("loginOK")
	public ResponseEntity<String> loginOk(MemberVO vo) {
		ResponseEntity<String> entity = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("text", "html", Charset.forName("UTF-8")));
		headers.add("Content-Type", "text/html; charset=utf-8");
		
		String msg = "<script>";
		MemberVO logVO = service.loginOk(vo);
		if(logVO!=null) {
			msg += "alert('로그인 성공하였습니다.');";
		}
			msg += "location.href='/';";
			msg += "</script>";
			
		entity = new ResponseEntity<String>(msg, headers, HttpStatus.OK);
		return entity;
	
	}
}
