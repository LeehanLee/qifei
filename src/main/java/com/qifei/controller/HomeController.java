package com.qifei.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qifei.model.CurrentUser;

@Controller
public class HomeController {

	private final static Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@GetMapping("/login")
	String login(){
		return "home/login";
	}
	
	@PostMapping("/login")
	ModelAndView login(String username, String password, HttpSession session) throws JsonProcessingException {		
		CurrentUser cuser=new CurrentUser();
		cuser.setUsername(username);
//		ArrayList<String> userRight =new ArrayList<String>();
//		userRight.add("accountlist");
//		userRight.add("index");
//		cuser.setAccountRight(userRight);
//		ObjectMapper mapper = new ObjectMapper();  
//		session.setAttribute("CurrentUser", mapper.writeValueAsString(cuser));
		
		return new ModelAndView("redirect:/#/admin/account");
    }
	
	@GetMapping("/")
	String index(){
		return "home/index";
	}
}
