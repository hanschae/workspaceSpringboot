package com.campus.myapp.service;

import java.util.List;

import com.campus.myapp.vo.ReplyVO;

public interface ReplyService {
	public int replyInsert(ReplyVO vo);
	public List<ReplyVO> replyList(int no);
	public int replyUpdate (ReplyVO vo);
	public int replyDelete (int reply_no, String userid);
}
