package com.board.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.board.app.service.BoardService;
import com.board.app.vo.PagingVO;

@Controller
public class BoardController {
	@Autowired
	BoardService service;
	
	ModelAndView mav = null;
	@GetMapping("/")
	public ModelAndView boardList(PagingVO pVO) {
		mav = new ModelAndView();
		// 총 레코드 수
		pVO.setTotalRecord(service.totalRecord(pVO));
		// DB 레코드 선택 - 페이지, 검색어
		mav.addObject("list", service.boardList(pVO));
		mav.addObject("pVO", pVO);
		
		mav.setViewName("boardList");
		return mav;
	}
}
