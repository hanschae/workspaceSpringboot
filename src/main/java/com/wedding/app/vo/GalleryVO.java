package com.wedding.app.vo;

public class GalleryVO {
	private int no;
	private String subject;
	private String content;
	private String staffid;
	private String writedate;
	
	
	@Override
	public String toString() {
		return "GalleryVO [no=" + no + ", subject=" + subject + ", content=" + content + ", staffid=" + staffid
				+ ", writedate=" + writedate + "]";
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
	public String getStaffid() {
		return staffid;
	}
	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}
	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	
	
}
