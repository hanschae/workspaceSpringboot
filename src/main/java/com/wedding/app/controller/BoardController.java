package com.wedding.app.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wedding.app.service.BoardService;

@RestController
@RequestMapping("/board/*")
public class BoardController {
	@Inject
	BoardService service;
}
