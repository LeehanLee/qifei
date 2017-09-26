package com.qifei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifei.entity.Department;
import com.qifei.mapper.IDepartmentMapper;
import com.qifei.model.DepartmentVM;

@Service
public class DepartmentService implements IDepartmentService {
	
	@Autowired
	IDepartmentMapper departmentMapper;
	

	@Override
	public Department getDepartment(String id) {
		// TODO Auto-generated method stub
		return departmentMapper.findById(id);
	}

	@Override
	public List<Department> getDepartmentList(int skip, int take) {
		// TODO Auto-generated method stub
		return departmentMapper.find(skip, take);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return departmentMapper.getCount();
	}

	@Override
	public int addDepartment(Department department) {
		// TODO Auto-generated method stub
		return departmentMapper.insert(department);
	}
	
	@Override
	public int editDepartment(Department department) {
		// TODO Auto-generated method stub
		return departmentMapper.update(department);
	}
	

	@Override
	public int deleteDepartment(String ids) {
		// TODO Auto-generated method stub
		return departmentMapper.delete(ids);
	}
	
	@Override
	public int updateAvaliable(String ids, boolean isAvaliable) {
		// TODO Auto-generated method stub
		return departmentMapper.updateAvaliableByIds(ids, isAvaliable);
	}

	@Override
	public DepartmentVM transformToDepartmentVM(Department entity) {
		DepartmentVM model = new DepartmentVM();
		model.setId(entity.getId());
		model.setName(entity.getName());
		model.setParentid(entity.getParentid());
		model.setAvailable(entity.isAvailable());
		model.setCreated(entity.getCreated());
		if (entity.getIdpath() != null) {
			model.setIdPath(entity.getIdpath().split("/"));	
		}
		String namePath = entity.getNamepath();
		if (namePath != null) {
			String[] paths = namePath.split("/");
			if (paths.length >= 2) {
				model.setParentName(paths[paths.length - 2]);	
			}
		}
		return model;
	}

	@Override
	public List<Department> getDepartmentListByParentId(String parentId, int skip, int take) {
		return departmentMapper.findByParentId(parentId, skip, take);
	}

}
