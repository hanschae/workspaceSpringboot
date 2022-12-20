package com.board.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.app.dao.BoardDAO;
import com.board.app.vo.BoardVO;
import com.board.app.vo.PagingVO;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardDAO dao;
	
	@Override
	public List<BoardVO> boardList(PagingVO pVO) {
		return dao.boardList(pVO);
	}

	@Override
	public int totalRecord(PagingVO pVO) {
		return dao.totalRecord(pVO);
	}
	
}
