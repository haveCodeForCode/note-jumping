package com.notejumping.system.config.redis;

import com.notejumping.common.until.SerializeUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Session缓存储存Dao层
 * RedisManager(redis底层实现）->RedisSessionDAO(redisSession底层实现）
 * @author taoya
 */
public class RedisSessionDAO extends AbstractSessionDAO {

    private static Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);
    /**
     * shiro-redis的session对象前缀
     */
    private RedisManager redisManager;

    @Autowired
    public void setRedisManager(RedisManager redisManager) {
        this.redisManager = redisManager;
    }

    /**
     * redis的会话密钥前缀常量
     * The Redis key prefix for the sessions
     */
    private String keyPrefix = "shiroSession_redisCache:";

    /**
     * Session 更新
     * @param session
     * @throws UnknownSessionException
     */
    @Override
    public void update(Session session) throws UnknownSessionException {
        this.saveSession(session);
    }

    /**
     * 存入 session
     *
     * @param session
     * @throws UnknownSessionException
     */
    private void saveSession(Session session) throws UnknownSessionException {
        if (session == null || session.getId() == null) {
            logger.error("session or session id is null");
            return;
        }

        byte[] key = getByteKey(session.getId());
        byte[] value = SerializeUtil.serialize(session);
        session.setTimeout(redisManager.getExpire() * 1000);
        this.redisManager.redisSet(key, value, redisManager.getExpire());
    }

    /**
     * 删除Sesseion
     *
     * @param session
     */
    @Override
    public void delete(Session session) {
        if (session == null || session.getId() == null) {
            logger.error("session or session id is null");
            return;
        }
        redisManager.redisDel(this.getByteKey(session.getId()));
    }

    /**
     *
     * @return
     */
    @Override
    public Collection<Session> getActiveSessions() {
        Set<Session> sessions = new HashSet<Session>();

        Set<byte[]> keys = redisManager.redisKeys(this.keyPrefix + "*");
        if (keys != null && keys.size() > 0) {
            for (byte[] key : keys) {
                Session s = (Session) SerializeUtil.deserialize(redisManager.redisGet(key));
                sessions.add(s);
            }
        }

        return sessions;
    }

    /**
     *
     * @param session
     * @return
     */
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
        return sessionId;
    }

    /**
     *
     * @param sessionId
     * @return
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (sessionId == null) {
            logger.error("session id is null");
            return null;
        }
        Session s = (Session) SerializeUtil.deserialize(redisManager.redisGet(this.getByteKey(sessionId)));
        return s;
    }

    /**
     * 获得byte[]型的key
     *
     * @param sessionId
     * @return
     */
    private byte[] getByteKey(Serializable sessionId) {
        String preKey = this.keyPrefix + sessionId;
        return preKey.getBytes();
    }

    public RedisManager getRedisManager() {
        return redisManager;
    }



}