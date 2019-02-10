package com.root.cognition.system.service;

import com.root.cognition.system.entity.User;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author LineInkBook
 */
@Service
public interface SessionService {

    List<User> listOnlineUser();

    Collection<Session> sessionList();

    boolean forceLogout(String sessionId);
}
