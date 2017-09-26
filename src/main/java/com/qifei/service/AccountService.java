package com.qifei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifei.entity.Account;
import com.qifei.entity.Department;
import com.qifei.mapper.IAccountMapper;
import com.qifei.mapper.IDepartmentMapper;
import com.qifei.model.AccountVM;

@Service
public class AccountService implements IAccountService {

	@Autowired
	IAccountMapper accountMapper;
	
	@Autowired
	IDepartmentMapper departmentMapper;
	
	@Override
	public Account getAccount(int id) {
		// TODO Auto-generated method stub
		return accountMapper.findById(id);
	}

	@Override
	public List<Account> getAccountList(int skip, int take) {
		// TODO Auto-generated method stub
		return accountMapper.find(skip, take);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return accountMapper.getCount();
	}

	@Override
	public int addAccount(Account account) {
		// TODO Auto-generated method stub
		return accountMapper.insert(account);
	}
	
	@Override
	public int editAccount(Account account) {
		// TODO Auto-generated method stub
		return accountMapper.update(account);
	}
	

	@Override
	public int deleteAccount(String ids) {
		// TODO Auto-generated method stub
		return accountMapper.delete(ids);
	}
	
	@Override
	public int updateAvaliable(String ids, boolean isAvaliable) {
		// TODO Auto-generated method stub
		return accountMapper.updateAvaliableByIds(ids, isAvaliable);
	}

	@Override
	public AccountVM transformToAccountVM(Account model) {
		AccountVM result = new AccountVM();
		result.setId(model.getId());
		result.setName(model.getName());
		result.setPassword(model.getPassword());
		result.setMobile(model.getMobile());
		result.setAvailable(model.isAvailable());
		result.setCreated(model.getCreated());
		result.setDepartmentid(model.getDepartmentid());
		Department dep = departmentMapper.findById(model.getDepartmentid());
		if (dep != null) {
			result.setDepartmentName(dep.getName());	
		}
		
		return result;
	}

}