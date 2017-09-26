package com.qifei.model;

import java.util.List;

public class TreeModel {

	private String id;
	private String name;
	private boolean selected;
	private boolean expanded;
	private List<TreeModel> children;
	private List<String> idPath;
	private String parentId;
	
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public List<String> getIdPath() {
		return idPath;
	}
	public void setIdPath(List<String> idPath) {
		this.idPath = idPath;
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

	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public boolean isExpanded() {
		return expanded;
	}
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
	public List<TreeModel> getChildren() {
		return children;
	}
	public void setChildren(List<TreeModel> children) {
		this.children = children;
	}

}
