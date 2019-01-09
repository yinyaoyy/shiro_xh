package com.yinyao;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

public class ShiroTest {

	public static void main(String[] args) {
		//JdbcRealm j = new JdbcRealm();   //jdbcrealm中有默认的select查询语句。
		OwnRealm o = new OwnRealm();
		DefaultSecurityManager d =  new DefaultSecurityManager();
		d.setRealm(o);
		//这是shiro的MD5加密
		HashedCredentialsMatcher m = new HashedCredentialsMatcher();
		//使用MD5加密
		m.setHashAlgorithmName("md5");
		//这是需要加密的次数
		m.setHashIterations(1);
		o.setCredentialsMatcher(m);
		SecurityUtils.setSecurityManager(d); 
		Subject subject = SecurityUtils.getSubject();
		//创建token
		UsernamePasswordToken token = new UsernamePasswordToken("yinyao", "xuyue521");
		//登录
		subject.login(token);
		subject.checkRoles("admin");
		subject.isPermitted("user:delete");
		System.out.println("身份认证成功");
		subject.logout();
	}
}
