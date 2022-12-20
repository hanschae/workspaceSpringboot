package com.board.app.vo;

public class BoardVO {
	private int no;
	private String subject;
	private String content;
	private String userid;
	private int hit;
	private String writedate;
	
	@Override
	public String toString() {
		return "BoardVO [no=" + no + ", subject=" + subject + ", content=" + content + ", userid=" + userid + ", hit="
				+ hit + ", writedate=" + writedate + "]";
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	
}
