package com.campus.myapp.service;

import java.util.List;

import com.campus.myapp.vo.DataVO;

public interface DataService {
	public List<DataVO> dataAllSelect();
	public int dataInsert(DataVO vo);
	public int downCount(int no); // 다운로드 횟수 증가
	public int newDownCount(int no);// 새로운 다운로드 횟수 선택하기
	public int hitCount(int no);
	public DataVO dataSelect(int no);
	public DataVO getFilenames(int no);// DB에 있는 파일명 선택
	public int dataUpdate(DataVO vo);
	public int dataDelete(int no, String userid);
}
