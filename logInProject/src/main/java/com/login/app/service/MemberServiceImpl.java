package com.login.app.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.login.app.dao.MemberDAO;
import com.login.app.vo.MemberVO;


@Service
public class MemberServiceImpl implements MemberService {
	@Inject
	MemberDAO dao;

	@Override
	public MemberVO loginOk(MemberVO vo) {
		return dao.loginOk(vo);
	}

}
