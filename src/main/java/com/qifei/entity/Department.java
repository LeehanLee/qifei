package com.qifei.entity;

import java.util.Date;

public class Department extends TreeNode {
	private Date created;
	private boolean available;

	private String namepath;
	
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getNamepath() {
		return namepath;
	}
	public void setNamepath(String namepath) {
		this.namepath = namepath;
	}
}
