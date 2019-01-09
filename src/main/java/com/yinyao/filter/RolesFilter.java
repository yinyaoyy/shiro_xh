package com.yinyao.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class RolesFilter extends AuthorizationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = getSubject(request,response);
        String[] roles = (String[]) mappedValue;
        if(StringUtils.isEmpty(roles)){
         return true;
        }
        for (String role:roles) {
            if(subject.hasRole(role)){
                return  true;
            }
        }
        return false;
    }
}
