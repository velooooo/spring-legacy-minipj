package com.mvc.pj.domain;

import java.sql.Date;

public class BoardDTO {
	//��������:�ʵ�:������Ƽ:property
	
	private int num;//��ȣ
	private String writer;//�ۼ���
	private String subject;//����
	private String content;//����
	private String pw;//��й�ȣ
	private Date reg_date;//�ۼ���
	private int hit;//��ȸ��
	private int re_group;//�� �׷�(����)
	private int re_step;//�� ����(����)
	private int re_depth;//�� ����
	private String ip;//������
	
	public BoardDTO() {}//�⺻������

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
