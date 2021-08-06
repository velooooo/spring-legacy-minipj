package com.mvc.pj.domain;

import java.sql.Date;

public class BoardDTO {
	//전역변수:필드:프로퍼티:property
	
	private int num;//번호
	private String writer;//작성자
	private String subject;//제목
	private String content;//내용
	private String pw;//비밀번호
	private Date reg_date;//작성일
	private int hit;//조회수
	private int re_group;//글 그룹(숫자)
	private int re_step;//글 순서(숫자)
	private int re_depth;//글 깊이
	private String ip;//아이피
	
	public BoardDTO() {}//기본생성자

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getRe_group() {
		return re_group;
	}

	public void setRe_group(int re_group) {
		this.re_group = re_group;
	}

	public int getRe_step() {
		return re_step;
	}

	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}

	public int getRe_depth() {
		return re_depth;
	}

	public void setRe_depth(int re_depth) {
		this.re_depth = re_depth;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
}
