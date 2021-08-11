package com.mvc.pj.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FaqController {
	@Autowired
	private SqlSession sqlSession;//º¯¼ö
	
	
	@RequestMapping("faq")
	public String mm() {
		return ".main.faq.faq";//ºä ¸®ÅÏ  //views/main.jsp
	}
}
