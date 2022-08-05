package com.wedding.app.vo;

public class StaffVO {
	private String staffid;
	private String staffpwd;
	
	
	
	@Override
	public String toString() {
		return "StaffVO [staffid=" + staffid + ", staffpwd=" + staffpwd + "]";
	}
	
	
	public String getStaffid() {
		return staffid;
	}
	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}
	public String getStaffpwd() {
		return staffpwd;
	}
	public void setStaffpwd(String staffpwd) {
		this.staffpwd = staffpwd;
	}
	
	
}
