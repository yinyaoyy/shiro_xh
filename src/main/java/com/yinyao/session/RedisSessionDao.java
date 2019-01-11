package com.yinyao.session;

import com.yinyao.utils.JedisUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.util.CollectionUtils;
import org.springframework.util.SerializationUtils;

import javax.annotation.Resource;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
//AbstractSessionDAO实现了SessionDAO，AbstractSessionDAO的午餐构造器生成了JavaUuidSessionIdGenerator，然后
public class RedisSessionDao extends AbstractSessionDAO {
    @Resource
    private JedisUtil jedisUtil;
    private  final String SHIRO_SESSION_PREFIX = "shiro_session";
    private byte[] getKet(String key){
    return  (SHIRO_SESSION_PREFIX+key).getBytes();
    }

    private  void saveSession(Session session){
    if ( session!= null && session.getId()!=null){
        byte[] key = getKet(session.getId().toString());
        byte[] value = SerializationUtils.serialize(session);
        jedisUtil.set(key,value);
        jedisUtil.expire(key,600);
    }
    }
    @Override
    protected Serializable doCreate(Session session) {
        //generateSessionId这个方法就是调用JavaUuidSessionIdGenerator中的generateId生成uuid的一个id，返回一个Serializable
        Serializable sessioId = generateSessionId(session);
        System.out.println("这是自己定义的sessiondao，第一次创建生成的sessionid:"+sessioId);
        //将生成的sessionid与session捆绑关联起来
        assignSessionId(session,sessioId);
        saveSession(session);
        return sessioId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        System.out.println("doReadSession:"+sessionId);
        if (sessionId == null){
            return null;
        }
        byte[] key = getKet(sessionId.toString());
        byte[] value = jedisUtil.get(key);
        return (Session)SerializationUtils.deserialize(value);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        saveSession(session);
    }

    @Override
    public void delete(Session session) {
       if(session==null && session.getId()==null){
           return;
       }
       byte[] key = getKet(session.getId().toString());
       jedisUtil.delete(key);
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<byte[]> set = jedisUtil.keys(SHIRO_SESSION_PREFIX);
        Set<Session>  sessions = new HashSet<>();
        if(CollectionUtils.isEmpty(set)){
            return sessions;
        }
        for (byte[] key:set) {
         Session s = (Session) SerializationUtils.deserialize(key);
         sessions.add(s);
        }
        return sessions;
    }
}
