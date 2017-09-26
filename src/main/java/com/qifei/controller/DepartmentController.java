package com.qifei.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qifei.common.Utils;
import com.qifei.entity.Department;
import com.qifei.entity.TreeNode;
import com.qifei.model.ActionButton;
import com.qifei.model.DepartmentVM;
import com.qifei.model.IdModel;
import com.qifei.model.JsonResult;
import com.qifei.model.TreeModel;
import com.qifei.service.IDepartmentService;
import com.qifei.thread.DepartmentUpdateThread;

@Controller
@RequestMapping("/department")
public class DepartmentController {

	private final static Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	
	@Autowired
	IDepartmentService service;
	
	@ResponseBody
	@PostMapping("/add")
	JsonResult add(@RequestBody Department model) throws JsonProcessingException {
	
		model.setId(UUID.randomUUID().toString());
 		model.setCreated(new Date());
		
 		String parentId = model.getParentid();
 		
		if (parentId != null && !"".equals(parentId)) {
			Department parent = service.getDepartment(model.getParentid());
			model.setIdpath(parent.getIdpath() + "/" + model.getId());
			model.setNamepath(parent.getNamepath() + "/" + model.getName());
		} else {
			model.setParentid(null);
			model.setIdpath(model.getId());
			model.setNamepath(model.getName());
		}
		
		if (service.addDepartment(model) == 1) {
			return new JsonResult(true, "添加成功", model.getId());
		}
		return new JsonResult(false, "添加失败");
    }

	@ResponseBody
	@PostMapping("/edit")
	JsonResult edit(@RequestBody Department model) throws JsonProcessingException {
	
		String parentId = model.getParentid();
		if (parentId != null && !"".equals(parentId)) {//MLGB，这里还需要同时递归更新这个节点的所有子节点的IdPath和NamePath
			Department parent = service.getDepartment(parentId);
			List<String> parentIdPath = Arrays.asList(parent.getIdpath().split("/"));
			if (parentIdPath.contains(model.getId())) {
				return new JsonResult(false, "修改失败，请勿选取当前部门及其下属部门作为上级部门");
			}
			model.setIdpath(parent.getIdpath() + "/" + model.getId());
			model.setNamepath(parent.getNamepath() + "/" + model.getName());
		} else {
			model.setParentid(null);
			model.setIdpath(model.getId());
			model.setNamepath(model.getName());
		}
		
		if (service.editDepartment(model) == 1) {
			Thread updateThread = new Thread(new DepartmentUpdateThread(model, service));
			updateThread.start();
			return new JsonResult(true, "修改成功", model.getId());
		}
		return new JsonResult(false, "修改失败");
    }
	
	@ResponseBody
	@GetMapping("/get")
	DepartmentVM get(@RequestParam(value="id", required=true)String id) {
		
		Department entity = service.getDepartment(id);
		
		DepartmentVM model = service.transformToDepartmentVM(entity);
		return model;		
	}
	
	@ResponseBody
	@GetMapping("/tree")
	List<TreeModel> tree(@RequestParam(value="parentid", required=false) String parentid, @RequestParam(value="selectedIds", required=false) String selectedIds) {
		
		List<String> selectedIdArray = selectedIds == null ? null : Arrays.asList(selectedIds.split(","));
		List<? extends TreeNode> departments = service.getDepartmentList(0, 10000);
		
		List<String> selectedIdPaths = null;
		if (selectedIdArray != null) {
			selectedIdPaths = new ArrayList<String>();
			Iterator<? extends TreeNode> it = departments.iterator();
			while(it.hasNext()) {
				TreeNode tn = it.next();
				if (selectedIdArray.contains(tn.getId())) {
					selectedIdPaths.add(tn.getIdpath());
				}
			}
		}
		
		return Utils.transformToTreeModelArray(departments, parentid, selectedIdArray, selectedIdPaths);
	}
	
	@ResponseBody
	@GetMapping("/actionButtons")
	List<ActionButton> actionButtons() {
		
		List<ActionButton> actionButtons = new ArrayList<>();
		actionButtons.add(new ActionButton("add", "添加"));
		actionButtons.add(new ActionButton("delete", "删除", true, "/department/delete"));
		actionButtons.add(new ActionButton("avaliable", "启用", true, "/department/avaliable"));
		actionButtons.add(new ActionButton("unavaliable", "禁用", true, "/department/unavaliable"));
		return actionButtons;
	}
	
	@ResponseBody
	@PostMapping("/delete")
	JsonResult delete(@RequestBody IdModel model) throws JsonProcessingException {
		
		int result = service.deleteDepartment(model.getIds());
		if (result >= 1) {
			return new JsonResult(true, "删除 "+ result +"条成功");
		}
		return new JsonResult(false, "删除失败");
    }
	
	@ResponseBody
	@PostMapping("/avaliable")
	JsonResult avaliable(@RequestBody IdModel model) throws JsonProcessingException {
		
		int result = service.updateAvaliable(model.getIds(), true);
		if (result >= 1) {
			return new JsonResult(true, "启用 "+ result +"条成功", model.getIds());
		}
		return new JsonResult(false, "启用失败");
    }
	
	@ResponseBody
	@PostMapping("/unavaliable")
	JsonResult unavaliable(@RequestBody IdModel model) throws JsonProcessingException {
		
		int result = service.updateAvaliable(model.getIds(), false);
		if (result >= 1) {
			return new JsonResult(true, "禁用 "+ result +"条成功", model.getIds());
		}
		return new JsonResult(false, "禁用失败");
    }
}
