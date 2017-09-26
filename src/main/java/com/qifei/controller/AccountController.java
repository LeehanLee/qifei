package com.qifei.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.qifei.entity.Account;
import com.qifei.model.AccountListModel;
import com.qifei.model.AccountVM;
import com.qifei.model.ActionButton;
import com.qifei.model.IdModel;
import com.qifei.model.JsonResult;
import com.qifei.model.TableTitle;
import com.qifei.service.IAccountService;

@Controller
@RequestMapping("/account")
public class AccountController {

	private final static Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	IAccountService service ;
	
	@GetMapping("")
	String index(){
		logger.info("进入index页面");
		logger.error("进入index页面写一个error日志");
		logger.warn("进入index页面写一个warn日志");
		logger.debug("进入index页面写一个debug日志");
		return "account/index";
	}
	
//	@GetMapping("/add")
//	String add(){
//		return "account/add";
//	}
	
	@ResponseBody
	@GetMapping("/get")
	AccountVM get(@RequestParam(value="id", required=false, defaultValue="0")int id) throws JsonProcessingException {
		Account model = service.getAccount(id);
		return service.transformToAccountVM(model);
    }
	
	@ResponseBody
	@PostMapping("/add")
	JsonResult add(@RequestBody Account model) throws JsonProcessingException {
		model.setCreated(new Date());
		
		if (service.addAccount(model) == 1) {
			return new JsonResult(true, "添加成功");
		}
		return new JsonResult(false, "添加失败");
    }
	
	@ResponseBody
	@PostMapping("/edit")
	JsonResult edit(@RequestBody Account model) throws JsonProcessingException {
		
		int result = service.editAccount(model);
		if (result == 1) {
			return new JsonResult(true, "修改成功");
		}
		return new JsonResult(false, "修改失败");
    }
	
	@ResponseBody
	@PostMapping("/delete")
	JsonResult delete(@RequestBody IdModel model) throws JsonProcessingException {
		
		int result = service.deleteAccount(model.getIds());
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
			return new JsonResult(true, "启用 "+ result +"条成功");
		}
		return new JsonResult(false, "启用失败");
    }
	
	@ResponseBody
	@PostMapping("/unavaliable")
	JsonResult unavaliable(@RequestBody IdModel model) throws JsonProcessingException {
		
		int result = service.updateAvaliable(model.getIds(), false);
		if (result >= 1) {
			return new JsonResult(true, "禁用 "+ result +"条成功");
		}
		return new JsonResult(false, "禁用失败");
    }
	
	@ResponseBody
	@GetMapping("/list")
	AccountListModel getAccountList(@RequestParam(value="page", required=false, defaultValue="0")int page, @RequestParam(value="take", required=false, defaultValue="10")int take) {
		
		List<Account> result = service.getAccountList((page-1) * take, take);
		AccountListModel model = new AccountListModel();
		model.setAccountDataList(result);
		
		List<TableTitle> titleList=new ArrayList<>();
		titleList.add(new TableTitle("id", "ID"));
		titleList.add(new TableTitle("name", "用户名"));
		titleList.add(new TableTitle("mobile", "电话"));
		titleList.add(new TableTitle("created", "添加时间"));
		titleList.add(new TableTitle("available", "启用"));		
		model.setAccountTitleList(titleList);
		
		model.setTotalCount(service.getCount());
        return model;		
	}
	
	@ResponseBody
	@GetMapping("/actionButtons")
	List<ActionButton> actionButtons() {
		
		List<ActionButton> actionButtons = new ArrayList<>();
		actionButtons.add(new ActionButton("add", "添加"));
		actionButtons.add(new ActionButton("delete", "删除", true, "/account/delete"));
		actionButtons.add(new ActionButton("avaliable", "启用", true, "/account/avaliable"));
		actionButtons.add(new ActionButton("unavaliable", "禁用", true, "/account/unavaliable"));
		return actionButtons;
	}
}
