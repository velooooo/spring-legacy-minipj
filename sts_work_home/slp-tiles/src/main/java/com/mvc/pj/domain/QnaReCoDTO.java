package com.mvc.pj.domain;

import java.sql.Date;

public class QnaReCoDTO {
	private int no;
	private int re_group;
	private int re_step;
	private int re_depth;
	private int co_is;
	private int co_group;
	private int co_step;
	private int co_depth;
	private String writer;
	private String subject;
	private String content;
	private String pw;
	private Date wdate;
	private Date ldate;
	private int hit;
	private String ip;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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
	public int getCo_is() {
		return co_is;
	}
	public void setCo_is(int co_is) {
		this.co_is = co_is;
	}
	public int getCo_group() {
		return co_group;
	}
	public void setCo_group(int co_group) {
		this.co_group = co_group;
	}
	public int getCo_step() {
		return co_step;
	}
	public void setCo_step(int co_step) {
		this.co_step = co_step;
	}
	public int getCo_depth() {
		return co_depth;
	}
	public void setCo_depth(int co_depth) {
		this.co_depth = co_depth;
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
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}
	public Date getLdate() {
		return ldate;
	}
	public void setLdate(Date ldate) {
		this.ldate = ldate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
