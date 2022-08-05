package com.wedding.app.vo;

public class ResrevationVO {
	private int no;
	private String hallname;
	private int scale;
	private String dday;
	private String makeup;
	private String dress;
	private String cday;
	private String subject;
	private String content;
	private String userid;
	private String stabdby;
	

	@Override
	public String toString() {
		return "ResrevationVO [no=" + no + ", hallname=" + hallname + ", scale=" + scale + ", dday=" + dday
				+ ", makeup=" + makeup + ", dress=" + dress + ", cday=" + cday + ", subject=" + subject + ", content="
				+ content + ", userid=" + userid + ", stabdby=" + stabdby + "]";
	}
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getHallname() {
		return hallname;
	}
	public void setHallname(String hallname) {
		this.hallname = hallname;
	}
	public int getScale() {
		return scale;
	}
	public void setScale(int scale) {
		this.scale = scale;
	}
	public String getDday() {
		return dday;
	}
	public void setDday(String dday) {
		this.dday = dday;
	}
	public String getMakeup() {
		return makeup;
	}
	public void setMakeup(String makeup) {
		this.makeup = makeup;
	}
	public String getDress() {
		return dress;
	}
	public void setDress(String dress) {
		this.dress = dress;
	}
	public String getCday() {
		return cday;
	}
	public void setCday(String cday) {
		this.cday = cday;
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
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getStabdby() {
		return stabdby;
	}
	public void setStabdby(String stabdby) {
		this.stabdby = stabdby;
	}
	
	
	
	
}
