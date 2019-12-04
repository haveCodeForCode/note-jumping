package com.notejumping.system.config;


import com.notejumping.common.config.Constant;
import com.notejumping.system.config.redis.RedisCacheManager;
import com.notejumping.system.config.redis.RedisManager;
import com.notejumping.system.config.redis.RedisSessionDAO;
import com.notejumping.system.config.shiro.BDSessionListener;
import com.notejumping.system.config.shiro.UserRealm;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 系统基本设置
 *
 * @author LineInkBook
 */
@Configuration
public class SystemConfig {

    /**
     * 使用类型缓存
     */
    @Value("${spring.cache.type}")
    private String cacheType;
    @Value("${server.servlet.session.timeout}")
    private int tomcatTimeout;

    /**
     * 认证控制器
     * @return
     */
    @Bean("securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm.
        securityManager.setRealm(new UserRealm());
        // 自定义缓存实现 使用redis
        // 放入缓存控制器
        if (Constant.CACHE_TYPE_REDIS.equals(cacheType)) {
            securityManager.setCacheManager(rediscacheManager());
        } else {
            securityManager.setCacheManager(ehCacheManager());
        }
        //放入session控制器
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

//*************************cacheManager缓存******************************

    /**
     * ehcache缓存初始化设置
     *
     * @return
     */
    @Bean("ehCacheManager")
    public EhCacheManager ehCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManager(cacheManager());
        return ehCacheManager;
    }

    /**
     * 创建EhCache，创建驱动
     *
     * @return
     */
    @Bean("cacheManagerSet")
    public CacheManager cacheManager() {
        return CacheManager.create();
    }


    /**
     * redis管理器预设
     *
     * @return
     */
    @Bean("redisManager")
    RedisManager redisManager() {
        return new RedisManager();
    }

    /**
     * cacheManager 缓存 （redis实现）
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    @Bean("rediscacheManager")
    RedisCacheManager rediscacheManager() {
        //初始化redis缓存对象
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        //将redis对象放入操作对象返回，完成redis初始化
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

//*************************session********************************
    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     */
    @Bean("redisSessionDAO")
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        RedisManager redisManager = new RedisManager();
        redisSessionDAO.setRedisManager(redisManager);
        return redisSessionDAO;
    }

    /**
     * sessionDao注入方式
     *
     * @return
     */
    @Bean("sessionDAO")
    public SessionDAO sessionDAO() {
        if (Constant.CACHE_TYPE_REDIS.equals(cacheType)) {
            return redisSessionDAO();
        } else {
            return new MemorySessionDAO();
        }
    }


    /**
     * session的管理
     */
    @Bean("sessionManager")
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        // 去掉shiro登录时url里的JSESSIONID
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        // session时间
        sessionManager.setGlobalSessionTimeout(tomcatTimeout * 1000);
        // session调用方法
        sessionManager.setSessionDAO(sessionDAO());
        // sessionListener监听接口组
        Collection<SessionListener> listeners = new ArrayList<SessionListener>();
        listeners.add(new BDSessionListener());
        // session操作器放入session监控
        sessionManager.setSessionListeners(listeners);
        return sessionManager;
    }


}
