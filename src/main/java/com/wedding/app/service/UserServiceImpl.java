package com.wedding.app.service;

import org.springframework.stereotype.Service;

import com.wedding.app.dao.UserDAO;

@Service
public class UserServiceImpl implements UserService {
	UserDAO dao;
}
