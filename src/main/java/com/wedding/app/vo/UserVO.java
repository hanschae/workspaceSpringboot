package com.wedding.app.vo;

public class UserVO {
	private String userid;
	private String userpwd;
	private String username;
	private String tel;
	private String gender;
	private String writedate;
	
	
	
	@Override
	public String toString() {
		return "UserVO [userid=" + userid + ", userpwd=" + userpwd + ", username=" + username + ", tel=" + tel
				+ ", gender=" + gender + ", writedate=" + writedate + "]";
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	
	
	
}
