package com.qifei.model;

import java.util.List;

import com.qifei.entity.Account;

public class AccountListModel {
	private int totalCount;
	
	private List<Account> accountDataList;
	
	private List<TableTitle> accountTitleList;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<Account> getAccountDataList() {
		return accountDataList;
	}

	public void setAccountDataList(List<Account> accountDataList) {
		this.accountDataList = accountDataList;
	}

	public List<TableTitle> getAccountTitleList() {
		return accountTitleList;
	}

	public void setAccountTitleList(List<TableTitle> accountTitleList) {
		this.accountTitleList = accountTitleList;
	}
	
	
}
