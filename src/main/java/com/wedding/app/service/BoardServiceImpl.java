package com.wedding.app.service;

import org.springframework.stereotype.Service;

import com.wedding.app.dao.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	BoardDAO dao;
}
