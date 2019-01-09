package com.yinyao.controller;

import com.alibaba.fastjson.JSONObject;
import com.yinyao.entity.Classes;
import com.yinyao.service.UserService;
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
	public Object getLogin(User user) {
	System.out.println(1111);
	Classes list = userService.getClassesInfoByUserName("1");
	List<User> u = userService.getRoleByUsername("yinyao");
     String s = JSONObject.toJSONString(list);
     String ss = JSONObject.toJSONString(u);
	System.out.println(s);
	System.out.println(ss);
	/*System.out.println(111);
	Subject s = SecurityUtils.getSubject();
	UsernamePasswordToken u = new UsernamePasswordToken(user.getUsername(), user.getPassword());
	s.login(u);
	System.out.println(s.isAuthenticated());*/
	return list;
		
	}

}
