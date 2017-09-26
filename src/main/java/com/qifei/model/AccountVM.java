package com.qifei.model;

import com.qifei.entity.Account;

public class AccountVM extends Account{

	private String departmentName;

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
}
