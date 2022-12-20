package com.board.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.board.app.vo.BoardVO;
import com.board.app.vo.PagingVO;

@Mapper
@Repository
public interface BoardDAO {
	// 글목록
	public List<BoardVO> boardList(PagingVO pVO);
	// 총 레코드 수
	public int totalRecord(PagingVO pVO);
}
