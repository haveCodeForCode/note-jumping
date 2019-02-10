package com.root.cognition.system.service.impl;

import com.root.cognition.system.entity.User;
import com.root.cognition.system.service.SessionService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

/**
 * @author LineInkBook
 */
public class SessionServiceImpl implements SessionService {
    private final SessionDAO sessionDAO;

    @Autowired
    public SessionServiceImpl(SessionDAO sessionDAO) {
        this.sessionDAO = sessionDAO;
    }

    @Override
    public List<User> listOnlineUser() {
        return null;
    }

    @Override
    public Collection<Session> sessionList() {
        return null;
    }

    @Override
    public boolean forceLogout(String sessionId) {
        return false;
    }
}
