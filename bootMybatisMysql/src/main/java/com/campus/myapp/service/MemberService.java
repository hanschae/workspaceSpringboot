package com.campus.myapp.service;

import java.util.List;

import com.campus.myapp.vo.MemberVO;
import com.campus.myapp.vo.ZipcodeVO;

public interface MemberService {
	public int idCheck(String userid);
	public List<ZipcodeVO> zipSearch(String doro);
	public int memberWrite(MemberVO vo);
	public MemberVO loginOk(MemberVO vo);
	public MemberVO getMember(String userid);
	public int memberEditOk(MemberVO vo);
	
}
