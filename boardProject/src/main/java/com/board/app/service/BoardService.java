package com.board.app.service;

import java.util.List;

import com.board.app.vo.BoardVO;
import com.board.app.vo.PagingVO;

public interface BoardService {
	// 글목록
	public List<BoardVO> boardList(PagingVO pVO);
	// 총 레코드 수
	public int totalRecord(PagingVO pVO);
	
}
