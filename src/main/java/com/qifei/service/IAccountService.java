package com.qifei.service;

import java.util.List;

import com.qifei.entity.Account;
import com.qifei.model.AccountVM;

public interface IAccountService {

	Account getAccount(int id);
	
	List<Account> getAccountList(int skip, int take);
	
	int getCount();
	
	int addAccount(Account account);
	
	int deleteAccount(String ids);

	int editAccount(Account account);
	
	int updateAvaliable(String ids, boolean isAvaliable);
	
	AccountVM transformToAccountVM(Account model);
}
