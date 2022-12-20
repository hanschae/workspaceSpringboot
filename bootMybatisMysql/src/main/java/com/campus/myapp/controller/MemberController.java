package com.campus.myapp.controller;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.campus.myapp.service.MemberService;
import com.campus.myapp.vo.MemberVO;
import com.campus.myapp.vo.ZipcodeVO;

@RestController // 리턴을 할 때, ModelAndView 또는 컨텐츠를 보낼 수 없다.
@RequestMapping("/member/*")
public class MemberController {
	@Inject
	MemberService service;
	// @RequestMapping("/member/memberForm")
	// @GetMapping		@PostMapping
	@GetMapping("memberForm")
	public ModelAndView memberForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/memberForm");
		return mav;
	}
	@GetMapping("idCheck") // 아이디 중복검사
	public ModelAndView idCheck(String userid) {
		ModelAndView mav = new ModelAndView();
		
		// DB조회 : 아이디가 존재하는지 확인
		int cnt = service.idCheck(userid); // 0, 1
		
		mav.addObject("idCnt", cnt);
		mav.addObject("userid", userid);
		
		mav.setViewName("member/idCheck");
		return mav;
	}
	@GetMapping("zipSearch")
	public ModelAndView zipSearch(String doro) {
		ModelAndView mav = new ModelAndView();
		List<ZipcodeVO> list = new ArrayList<ZipcodeVO>();
		
		if(doro!=null && !doro.equals("")) {
		list = service.zipSearch(doro);
		}
		// System.out.println(list.size());
		mav.addObject("zipList",list);
				
		mav.setViewName("member/zipSearch");
		return mav;
	}
	
	// 회원가입하기
	@PostMapping("memberWrite")
	public ResponseEntity<String> memberWrite(MemberVO vo) {
	
		// RestController 에서는 ResponseBody를 보낼 수 있다.
		// 클라이언트에게 데이터와 뷰파일을 담을 수 있는 객체이며, 뷰페이지를 별도로 만들 필요가 없다.
		ResponseEntity<String> entity = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("text","html",Charset.forName("UTF-8")));
		headers.add("Content-Type", "text/html; charset=utf-8");
		
		try{ // 회원등록 > 로그인폼으로 이동
			int result = service.memberWrite(vo);
			
			String msg = "<script>";
			msg += "alert('회원가입에 성공하였습니다. 로그인페이지로 이동합니다.');";
			msg += "location.href='/member/login'";
			msg += "</script>";
			
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.OK); // 성공 : 200
		}catch(Exception e) { // 회원등록 실패 > 이전페이지(history:자바스크립트)
			String msg = "<script>";
			msg += "alert('회원가입에 실패하였습니다. 이전페이지로 이동합니다.');";
			msg += "history.back();";
			msg += "</script>";
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST);
			
			e.printStackTrace();
		}
		return entity;
	}
	// 로그인 폼
	@GetMapping("login")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/login");
		return mav;
	}
	// 로그인 DB조회
	@PostMapping("loginOK")
	public ModelAndView loginOk(MemberVO vo, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		MemberVO logVO = service.loginOk(vo);
		
		if(logVO!=null) { // 로그인 성공
			session.setAttribute("logId", logVO.getUserid());
			session.setAttribute("logName", logVO.getUsername());
			session.setAttribute("logStatus", "Y");
			mav.setViewName("redirect:/");
		}else { // 로그인 실패
			mav.setViewName("redirect:login");
		}
		return mav;
	}
	// 로그아웃
	@GetMapping("logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate(); // 세션의 로그인정보를 제거
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/");
		return mav;
	}
	// 회원정보 수정 폼 : 세션의 아이디와 같은 회원정보를 선택한 후 뷰페이지로 이동해야한다.
	@GetMapping("memberEdit")
	public ModelAndView memberEdit(HttpSession session) {
		String userid = (String)session.getAttribute("logId");
		
		MemberVO vo = service.getMember(userid);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("vo", vo);
		mav.setViewName("member/memberEdit");
		
		return mav;
	}
	// 회원정보 수정 DB 업데이트
	@PostMapping("memberEditOk")
	public ResponseEntity<String> memberEditOk(MemberVO vo) { // @RequestParam, @ReturnValue
		
		ResponseEntity<String> entity = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("text","html", Charset.forName("UTF-8")));
		headers.add("Content-Type", "text/html; charset=UTF-8");
		
		String msg = "<script>";
		int cnt = service.memberEditOk(vo);
		
		if(cnt>0) { // 수정됨
			msg += "alert('회원정보가 수정되었습니다.')";
		}else { // 수정못함
			msg += "alert('회원정보수정에 실패하였습니다.')";
		}
		msg += "location.href='/member/memberEdit';</script>";
		
		entity = new ResponseEntity<String>(msg, headers, HttpStatus.OK);
		return entity;
		
	}
}
