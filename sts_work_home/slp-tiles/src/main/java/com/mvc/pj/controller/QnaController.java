package com.mvc.pj.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QnaController {
	@Autowired
	private SqlSession sqlSession;//����
	
	
	@RequestMapping("qna-list")
	public String mm() {
		return ".main.qna.qna-list";//�� ����  //views/main.jsp
	}
}
