package com.campus.myapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.campus.myapp.vo.ReplyVO;

@Mapper
@Repository
public interface ReplyDAO {
	public int replyInsert(ReplyVO vo);
	public List<ReplyVO> replyList(int no);
	public int replyUpdate (ReplyVO vo);
	public int replyDelete (int reply_no, String userid);
}
