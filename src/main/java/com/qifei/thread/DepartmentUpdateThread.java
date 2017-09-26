package com.qifei.thread;

import java.util.List;

import com.qifei.entity.Department;
import com.qifei.service.IDepartmentService;

public class DepartmentUpdateThread implements Runnable {

	private IDepartmentService service;
	
	private Department parent;
	
	public DepartmentUpdateThread(Department parent, IDepartmentService service) {
		this.parent = parent;
		this.service = service;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		updateChildren(this.parent);
	}
	
	private void updateChildren(Department parent) {
		List<Department> children = service.getDepartmentListByParentId(parent.getId(), 0, 99999);
		if (children != null && !children.isEmpty()) {

			for(Department child : children) {
				child.setIdpath(parent.getIdpath() + "/" + child.getId());
				child.setNamepath(parent.getNamepath() + "/" + child.getName());
				service.editDepartment(child);
				
				updateChildren(child);
			}
		}
	}

}
