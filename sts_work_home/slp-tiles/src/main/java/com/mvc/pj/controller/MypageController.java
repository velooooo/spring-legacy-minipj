package com.mvc.pj.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MypageController {
	@Autowired
	private SqlSession sqlSession;//º¯¼ö
	
	
	@RequestMapping("member/mypage")
	public String mm() {
		return ".main.member.mypage";//ºä ¸®ÅÏ
	}
}
