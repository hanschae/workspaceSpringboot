package com.campus.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campus.myapp.dao.BoardDAO;
import com.campus.myapp.vo.BoardVO;
import com.campus.myapp.vo.PagingVO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDAO dao;

	@Override
	public List<BoardVO> boardList(PagingVO pVO) {
		return dao.boardList(pVO);
	}

	@Override
	public int boardWriteOk(BoardVO vo) {
		return dao.boardWriteOk(vo);
	}

	@Override
	public BoardVO getBoard(int no) {
		return dao.getBoard(no);
	}

	@Override
	public int boardEditOk(BoardVO vo) {
		return dao.boardEditOk(vo);
	}

	@Override
	public int boardDel(int no, String userid) {
		return dao.boardDel(no, userid);
	}

	@Override
	public void hitCount(int no) {
		dao.hitCount(no);
	}

	@Override
	public int totalRecord(PagingVO pVO) {
		return dao.totalRecord(pVO);
	}

	@Override
	public int boardMultiDel(BoardVO vo) {
		return dao.boardMultiDel(vo);
	}
	
}
