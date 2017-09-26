package com.qifei.model;

import java.util.Date;

public class DepartmentVM {
	private String id;
	private String name;
	private String parentid;
	private Date created;
	private boolean available;
	private String parentName;
	
	private String[] idPath;
//	private String[] namepath;
	
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
//	public String[] getNamepath() {
//		return namepath;
//	}
//	public void setNamepath(String[] namepath) {
//		this.namepath = namepath;
//	}

	public String[] getIdPath() {
		return idPath;
	}
	public void setIdPath(String[] idPath) {
		this.idPath = idPath;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}

}
