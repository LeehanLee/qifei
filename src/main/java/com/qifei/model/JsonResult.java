package com.qifei.model;

public class JsonResult {
	private boolean success;
	private String message;
	private String modifiedId;

	public String getModifiedId() {
		return modifiedId;
	}

	public void setModifiedId(String modifiedId) {
		this.modifiedId = modifiedId;
	}

	public JsonResult(boolean s, String m) {
		this.setSuccess(s);
		this.setMessage(m);
	}
	
	public JsonResult(boolean s, String m, String modifiedId) {
		this.setSuccess(s);
		this.setMessage(m);
		this.setModifiedId(modifiedId);
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
