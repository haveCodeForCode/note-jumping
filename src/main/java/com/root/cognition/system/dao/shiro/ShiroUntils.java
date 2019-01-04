package com.root.cognition.system.dao.shiro;

import com.root.cognition.system.entity.user.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;
import java.util.List;

/**
 * shiro工具类
 * @author LineInkBook
 */
public class ShiroUntils {

    /**
     *  Shiro获取验证对象
     */
    public static Subject getSecuritySubject(){
        return SecurityUtils.getSubject();
    }

    /**
     * 获取session内的ObeJect
     * @return
     */
    public static Object getSessionObject(){
        Object object = getSecuritySubject().getPrincipal();
        return object;
    }
    /**
     *  通过session获取userid
     * @return
     */
    public static String getUserId(){
        User user =  (User) ShiroUntils.getSessionObject();
        return user.getId();
    }
}
