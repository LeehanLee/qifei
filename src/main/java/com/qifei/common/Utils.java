package com.qifei.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.qifei.entity.TreeNode;
import com.qifei.model.TreeModel;

public class Utils {
	public static boolean needExpanded(List<String> selectedIdPaths, String nodeId) {
		boolean result = false;
		for (String idpaths : selectedIdPaths) {
			if (idpaths != null) {
				List<String> paths = Arrays.asList(idpaths.split("/"));
				if (paths.contains(nodeId)) {
					result = true;
					break;
				}
			}
		}
		return result;
	}
	
	public static List<TreeModel> transformToTreeModelArray(List<? extends TreeNode> departments, String parentid, List<String> selectedIds, List<String> selectedIdPath) {
 
		List<TreeModel> result = new ArrayList<TreeModel>();
		
		Iterator<? extends TreeNode> iterator =  departments.iterator();
		//这种方式整个list最少会遍历两次
		while(iterator.hasNext()){
			TreeNode node = iterator.next();
			String cpid = node.getParentid();
			if ((cpid == null && parentid == null) || (parentid != null && parentid.equals(cpid)) || (cpid != null && cpid.equals(parentid))) {
				
//				iterator.remove();
				
				TreeModel n = new TreeModel();
				String nodeId = node.getId();
				n.setId(nodeId);
				n.setParentId(cpid);
				n.setIdPath(Arrays.asList(node.getIdpath().split("/")));
				
				if (selectedIds != null && selectedIds.contains(nodeId)) {
					n.setSelected(true);
				}
				n.setName(node.getName());
				List<TreeModel> children = transformToTreeModelArray(departments, node.getId(), selectedIds, selectedIdPath);
				n.setChildren(children);
				
				if (selectedIdPath != null) {
					n.setExpanded(needExpanded(selectedIdPath, n.getId()));
				}
				
				result.add(n);
			}
		}
		return result;
	}
}
