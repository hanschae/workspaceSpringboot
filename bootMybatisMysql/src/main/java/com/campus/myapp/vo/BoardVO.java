package com.campus.myapp.vo;

import java.util.List;

public class BoardVO {
	private int no;
	private String subject;
	private String content;
	private String userid;
	private String ip;
	private int hit;
	private String writedate;
	
	// 여러개의 레코드 번호를 저장할 컬렉션 선언
	private List<Integer> noList;	
	
	// 댓글 갯수 저장할 변수
	private int reply_count;
	
	@Override
	public String toString() {
		return "BoardVO [no=" + no + ", subject=" + subject + ", content=" + content + ", userid=" + userid + ", ip="
				+ ip + ", hit=" + hit + ", writedate=" + writedate + "]";
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
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
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
	public List<Integer> getNoList() {
		return noList;
	}
	public void setNoList(List<Integer> noList) {
		this.noList = noList;
	}

	public int getReply_count() {
		return reply_count;
	}

	public void setReply_count(int reply_count) {
		this.reply_count = reply_count;
	}
	
}
