package com.qifei.service;

import java.util.List;

import com.qifei.entity.Department;
import com.qifei.model.DepartmentVM;

public interface IDepartmentService {
	
	Department getDepartment(String id);
	
	List<Department> getDepartmentList(int skip, int take);
	
	List<Department> getDepartmentListByParentId(String parentId, int skip, int take);
	
	int getCount();
	
	int addDepartment(Department Department);
	
	int deleteDepartment(String ids);

	int editDepartment(Department Department);
	
	int updateAvaliable(String ids, boolean isAvaliable);
	
	DepartmentVM transformToDepartmentVM(Department entity);
}
