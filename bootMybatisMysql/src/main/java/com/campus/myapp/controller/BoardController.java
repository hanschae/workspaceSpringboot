package com.campus.myapp.controller;

import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.campus.myapp.service.BoardService;
import com.campus.myapp.vo.BoardVO;
import com.campus.myapp.vo.PagingVO;

@RestController
@RequestMapping("/board/*")
public class BoardController {
	@Autowired
	BoardService service;
	
	ModelAndView mav = null;
	// 게시판
	@GetMapping("boardList")
	public ModelAndView boardList(PagingVO pVO) {
		mav = new ModelAndView();
		
		// 총 레코드 수
		pVO.setTotalRecord(service.totalRecord(pVO));
		
		// System.out.println(pVO.toString());
		
		// DB 레코드 선택 - 페이지, 검색어
		mav.addObject("list", service.boardList(pVO));
		mav.addObject("pVO", pVO);
		
		mav.setViewName("board/boardList");
		return mav;
	}
	// 글쓰기 폼
	@GetMapping("boardForm")
	public ModelAndView boardForm() {
		mav = new ModelAndView();
		mav.setViewName("board/boardForm");
		return mav;
	}
	// 글쓰기 DB
	@PostMapping("boardFormOk")
	public ResponseEntity<String> boardFormOk(BoardVO vo, HttpServletRequest request){
		vo.setUserid((String)request.getSession().getAttribute("logId")); // 세션 로그인 아이디
		vo.setIp(request.getRemoteAddr()); // 접속자의 아이피
		
		String msg = "<script>";
		try {
			service.boardWriteOk(vo);
			msg += "location.href='/board/boardList';";
		}catch(Exception e){
			msg += "alert('글등록이 실패했습니다.');";
			msg += "history.go(-1)";
		}
		msg += "</script>";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("text","html", Charset.forName("UTF-8")));
		headers.add("Content-Type", "text/html; charset=UTF-8");
		ResponseEntity<String> entity = new ResponseEntity<String>(msg, headers, HttpStatus.OK);
		return entity;
		
	}
	// 글내용보기
	@GetMapping("boardView")
	public ModelAndView boardView(@RequestParam("no") int no, PagingVO pVO) {
		// 조회수 증가
		service.hitCount(no);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", service.getBoard(no));
		mav.addObject("pVO", pVO);
		mav.setViewName("board/boardView");
		return mav;
	}
	// 글 수정 폼
	@GetMapping("boardEdit/{num}")
	public ModelAndView boardEdit(@PathVariable("num") int no) {
		mav = new ModelAndView();
		
		mav.addObject("vo", service.getBoard(no));
		mav.setViewName("board/boardEdit");
		
		return mav;
	}
	// 글 수정 : DB업데이트
	@PostMapping("boardEditOk")
	public ResponseEntity<String> boardEditOk(BoardVO vo, HttpSession session){
		// 세션의 아이디를 구한다.
		vo.setUserid((String)session.getAttribute("logId"));
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("text","html",Charset.forName("UTF-8")));
		headers.add("Content-Type", "text/html; charset-UTF-8");
		String msg = "<script>";
		try {
			service.boardEditOk(vo);
			// 수정되었을때 - 글내용보기
			msg += "alert('수정완료되었습니다. 글내용보기로 이동합니다.');";
			msg += "location.href='/board/boardView?no="+vo.getNo()+"';";
					
		}catch(Exception e){
			e.printStackTrace();
			// 수정실패일때 - 수정으로 보내기(history)
			msg += "alert('글수정 실패하였습니다.');";
			msg += "history.go(-1);";
		}
		msg += "</script>";
		
		return new ResponseEntity<String>(msg, headers, HttpStatus.OK);
	}
	// 글 삭제
	@GetMapping("boardDel")
	public ModelAndView boardDel(int no, HttpSession session) {
		int cnt = service.boardDel(no, (String)session.getAttribute("logId"));
		mav = new ModelAndView();
		if(cnt>0) {
			mav.setViewName("redirect:boardList");
		}else {
			mav.setViewName("redirect:boardView");
		}
		return mav;
	}
	// 여러개의 레코드 삭제
	@PostMapping("/multiDel")
	public ModelAndView multiDel(BoardVO vo) {
		int cnt = service.boardMultiDel(vo);
		System.out.println("삭제된 레코드 수 : "+cnt);
		
		mav = new ModelAndView();
		mav.setViewName("redirect:boardList");
		return mav;
	}
}
