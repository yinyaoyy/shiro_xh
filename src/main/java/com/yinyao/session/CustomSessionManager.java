package com.yinyao.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;

import javax.servlet.ServletRequest;
import java.io.Serializable;

public class CustomSessionManager extends DefaultWebSessionManager {
    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        //sessionKey中存在ServletRequest，里面有request
        Serializable sessionid = getSessionId(sessionKey);
        ServletRequest servletRequest = null;
        if(sessionKey instanceof WebSessionKey){
            servletRequest = ((WebSessionKey) sessionKey).getServletRequest();
        }
        if(servletRequest!=null && sessionid!=null){
        Session session = (Session) servletRequest.getAttribute(sessionid.toString());
        if(session!=null){
            return  session;
        }
        }
        Session session = super.retrieveSession(sessionKey);
        if(servletRequest!=null && sessionid!=null){
        servletRequest.setAttribute(sessionid.toString(),session);
        }
        return session;
    }
}
