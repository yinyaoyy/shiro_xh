package com.yinyao.controller;

import com.alibaba.fastjson.JSONObject;
import com.yinyao.entity.Classes;
import com.yinyao.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yinyao.entity.User;
import javax.annotation.Resource;
import java.util.List;

@Controller
public class LoginController {
	@Resource
	private UserService userService;
@RequestMapping(value="login",method=RequestMethod.POST,produces="application/json;charset=utf-8")
@ResponseBody
	public Object getLogin(User user) throws Exception {
//	System.out.println(1111);
//	Classes list = userService.getClassesInfoByUserName("1");
//	User u = userService.getRoleByUsername("yinyao");
//     String s = JSONObject.toJSONString(list);
//     String ss = JSONObject.toJSONString(u);
//	System.out.println(s);
//	System.out.println(ss);
//	/*System.out.println(111);
	    Subject s = SecurityUtils.getSubject();
			UsernamePasswordToken u = new UsernamePasswordToken(user.getUsername(), user.getPassword());
	    try {
			s.login(u);
		}catch (Exception e){
	    	return e.getMessage();
		}

	if(s.isAuthenticated()){
		System.out.println(s.hasRole("超级管理员"));
		return "认证成功！！！";
	}
		return "认证失败！！";
	}
     @RequestMapping(value = "/testRole",method = RequestMethod.GET)
     @ResponseBody
	public String testRole(){

	return "testRole success!";
	}
	@RequestMapping(value = "/testRole1",method = RequestMethod.GET)
	@ResponseBody
	public String testRole1(){

	return "testRole1 success!";
	}
	@RequestMapping(value = "/testPerms",method = RequestMethod.GET)
	@ResponseBody
	public String testPerms(){
		return "testPerms success!";
	}
	@RequestMapping(value = "/testPerms1",method = RequestMethod.GET)
	@ResponseBody
	public String testPerms1(){

	return "testPerms1 success!";
	}
	@RequestMapping(value = "/testPerms2",method = RequestMethod.GET)
	@ResponseBody
	public String testPerms2(){
		return "testPerms2 success!";
	}

}
