package com.campus.myapp.vo;

import java.util.List;

public class DataVO {
	private int no;
	private String title;
	private String content;
	private String userid;
	private int downcount;
	private int hit;
	private String writedate;
	private String filename1;
	private String filename2;
	
	private List<String> delFile; // 삭제한 파일명이 보관될 컬렉션
	
	@Override
	public String toString() {
		return "DataVO [no=" + no + ", title=" + title + ", content=" + content + ", userid=" + userid + ", downcount="
				+ downcount + ", hit=" + hit + ", writedate=" + writedate + ", filename1=" + filename1 + ", filename2="
				+ filename2 + "]";
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public int getDowncount() {
		return downcount;
	}
	public void setDowncount(int downcount) {
		this.downcount = downcount;
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
	public String getFilename1() {
		return filename1;
	}
	public void setFilename1(String filename1) {
		this.filename1 = filename1;
	}
	public String getFilename2() {
		return filename2;
	}
	public void setFilename2(String filename2) {
		this.filename2 = filename2;
	}
	public List<String> getDelFile() {
		return delFile;
	}
	public void setDelFile(List<String> delFile) {
		this.delFile = delFile;
	}
	
}
