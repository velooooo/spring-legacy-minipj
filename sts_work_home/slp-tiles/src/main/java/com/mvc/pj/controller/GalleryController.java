package com.mvc.pj.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GalleryController {
	@Autowired
	private SqlSession sqlSession;//����
	
	
	@RequestMapping("gal-list")
	public String mm() {
		return ".main.gallery.gal-list";//�� ����  //views/main.jsp
	}
}
