package com.drl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainController extends BaseController{

	@RequestMapping("/index.do")
	public String showIndex() {
		return "index";
	}
	
	@RequestMapping("/error.do")
	public String showError() {
		return "error";
	}
	
//	@RequestMapping("/file.do")
//	public String showFile() {
//		return "file";
//	}
}
