package com.qifei.model;

public class ActionButton {

	private String url;
	private String text;
	private String id;
	private boolean isAjaxAction;
	
	public boolean isAjaxAction() {
		return isAjaxAction;
	}

	public void setAjaxAction(boolean isAjaxAction) {
		this.isAjaxAction = isAjaxAction;
	}
	public ActionButton(String id, String text) {
		this.id = id;
		this.text = text;
	}

	public ActionButton(String id, String text, Boolean isAjaxAction, String url) {
		this.id = id;
		this.text = text;
		this.url = url;
		this.isAjaxAction = isAjaxAction;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
