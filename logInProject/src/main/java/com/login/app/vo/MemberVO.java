package com.login.app.vo;

public class MemberVO {
	private String userid;
	private String userpwd;
	private String username;
	private String tel;
	private String email;
	private String writedate;
	
	
	@Override
	public String toString() {
		return "MemberVO [userid=" + userid + ", userpwd=" + userpwd + ", username=" + username + ", tel=" + tel
				+ ", email=" + email + ", writedate=" + writedate + "]";
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	
}
