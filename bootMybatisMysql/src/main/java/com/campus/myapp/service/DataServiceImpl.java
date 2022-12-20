package com.campus.myapp.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.campus.myapp.dao.DataDAO;
import com.campus.myapp.vo.DataVO;

@Service
public class DataServiceImpl implements DataService {
	@Inject
	DataDAO dao;
	
	@Override
	public List<DataVO> dataAllSelect() {
		return dao.dataAllSelect();
	}

	@Override
	public int dataInsert(DataVO vo) {
		return dao.dataInsert(vo);
	}

	@Override
	public int downCount(int no) {
		return dao.downCount(no);
	}

	@Override
	public int newDownCount(int no) {
		return dao.newDownCount(no);
	}

	@Override
	public int hitCount(int no) {
		return dao.hitCount(no);
	}

	@Override
	public DataVO dataSelect(int no) {
		return dao.dataSelect(no);
	}

	@Override
	public DataVO getFilenames(int no) {
		return dao.getFilenames(no);
	}

	@Override
	public int dataUpdate(DataVO vo) {
		return dao.dataUpdate(vo);
	}

	@Override
	public int dataDelete(int no, String userid) {
		return dao.dataDelete(no, userid);
	}

}
