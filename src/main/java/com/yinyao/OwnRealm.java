package com.yinyao;

import java.util.*;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.yinyao.entity.Permission;
import com.yinyao.entity.Role;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.yinyao.entity.User;
import com.yinyao.service.UserService;
import org.springframework.util.StringUtils;
//需要继承AuthorizingRealm
public class OwnRealm extends AuthorizingRealm {
	@Resource
	private UserService userService;
	public static void main(String[] args) {
		Md5Hash m = new Md5Hash("xuyue521","yinyao");
		System.out.println(m.toString());
	}
//这里就是授权，主体传过来的信息查询用户的角色和权限
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		User  user = userService.getRoleByUsername(username);
		System.out.println(JSONObject.toJSON(user));
		List<Role> role = user.getRoleList();
		Set<String> roles = new HashSet<>();
		Set<String> permissions = new HashSet<>();
		for (int i = 0; i < role.size(); i++) {
			if(!StringUtils.isEmpty(role.get(i).getRolename())){
				roles.add(role.get(i).getRolename());
			}
			List<Permission> permissions1 = role.get(i).getPermissionList();
			for (int j = 0; j < permissions1.size(); j++) {
				if(!StringUtils.isEmpty(permissions1.get(j).getPermission())){
					permissions.add(permissions1.get(j).getPermission());
				}
			}
		}
		System.out.println(JSONObject.toJSON(permissions));
		SimpleAuthorizationInfo a = new SimpleAuthorizationInfo();
	    a.setRoles(roles);
	    a.setStringPermissions(permissions);
		return a;
	}

	@Override//认证过程
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
      //从主体传过来的认证信息中获取用户名
		String username = (String) token.getPrincipal();
		//根据用户名去数据库获取用户信息
		User user = userService.getUserInfoByUserName(username);
		String password = user.getPassword();
		if(password==null) {
			return null;
		}
		SimpleAuthenticationInfo s = new SimpleAuthenticationInfo(username,password,"myrealm");
		//加盐，也就是加密
		//s.setCredentialsSalt(ByteSource.Util.bytes("yinyao"));
		return s;
	}


  
}
