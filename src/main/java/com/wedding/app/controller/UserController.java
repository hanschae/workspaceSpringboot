package com.wedding.app.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wedding.app.service.UserService;

@RestController
@RequestMapping("/user/*")
public class UserController {
	@Inject
	UserService service;
}
