package com.qifei.entity;

public class TreeNode {

	private String id;
	private String name;
	private String parentid;
	private String idpath;

	public String getIdpath() {
		return idpath;
	}
	public void setIdpath(String idpath) {
		this.idpath = idpath;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	
}
