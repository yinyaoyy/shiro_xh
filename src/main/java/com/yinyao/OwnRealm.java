package com.yinyao;

import java.util.*;

import javax.annotation.Resource;

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

public class OwnRealm extends AuthorizingRealm {
	@Resource
	private UserService userService;
	public static void main(String[] args) {
		Md5Hash m = new Md5Hash("xuyue521","yinyao");
		System.out.println(m.toString());
	}
	Map<String,String> map = new HashMap<>();
	{//对应的密码为xuyue521，盐为yinyao，得到的md5加密
		map.put("yinyao", "xuyue521");
		map.put("myrealm", "myrealm");
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		List<User>  roleList = userService.getRoleByUsername(username);
		Set<String> roles = new HashSet<>();
		for (int i = 0; i < roleList.size(); i++) {
			roles.add(roleList.get(i).getRoleList().getRolename());
		}
		Set<String> permissions = getPermissionsyUsername(username);
		SimpleAuthorizationInfo a = new SimpleAuthorizationInfo();
	    a.setRoles(roles);
	    a.setStringPermissions(permissions);
		return a;
	}

	private Set<String> getPermissionsyUsername(String username) {
		Set<String> set = new HashSet<>();
		set.add("user:delete");
		set.add("user:add");
		return set;
	}

	private Set<String> getRoleByUsername(String username) {
		Set<String> set = new HashSet<>();
		set.add("admin");
		set.add("user");
		return set;
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
		//加盐
		//s.setCredentialsSalt(ByteSource.Util.bytes("yinyao"));
		return s;
	}

	
  
}
