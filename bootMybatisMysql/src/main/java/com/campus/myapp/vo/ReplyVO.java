package com.campus.myapp.vo;

public class ReplyVO {
	private int reply_no;
	private int no;
	private String userid;
	private String comment;
	private String writedate;
	
	@Override
	public String toString() {
		return "ReplyVO [reply_no=" + reply_no + ", no=" + no + ", userid=" + userid + ", comment=" + comment
				+ ", writedate=" + writedate + "]";
	}
	
	public int getReply_no() {
		return reply_no;
	}
	public void setReply_no(int reply_no) {
		this.reply_no = reply_no;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	
}
