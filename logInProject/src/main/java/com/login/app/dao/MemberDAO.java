package com.login.app.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.login.app.vo.MemberVO;

@Mapper
@Repository
public interface MemberDAO {
	public MemberVO loginOk(MemberVO vo);

}
