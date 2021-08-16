package com.mvc.pj.domain;

import java.sql.Date;

public class CommentDTO {
	private int no;
	private String writer;
	private String pw;
	private String content;
	private String board_id;
	private int co_group;
	private int co_step;
	private int co_depth;
	private Date wdate;
	private String ip;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getBoard_id() {
		return board_id;
	}
	public void setBoard_id(String board_id) {
		this.board_id = board_id;
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
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
