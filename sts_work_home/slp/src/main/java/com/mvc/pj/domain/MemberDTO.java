package com.mvc.pj.domain;
import java.util.Date;
//모델빈
public class MemberDTO {
	//전역변수:필드:프로퍼티:property
	
	private int num;
	private String id;
	private String pw;
	private String name;
	
	private String email;
	private String tel;
	
	private String addr_code;
	private String addr;
	private String addr_detail;
	private Date reg_date;
	
	public MemberDTO() {}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddr_code() {
		return addr_code;
	}

	public void setAddr_code(String addr_code) {
		this.addr_code = addr_code;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getAddr_detail() {
		return addr_detail;
	}

	public void setAddr_detail(String addr_detail) {
		this.addr_detail = addr_detail;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	
	
}
