package com.root.cognition.system.config;


import com.root.cognition.common.config.Constant;
import com.root.cognition.common.config.DataDic;
import com.root.cognition.common.redis.RedisCacheManager;
import com.root.cognition.common.redis.RedisManager;
import com.root.cognition.common.redis.RedisSessionDAO;
import com.root.cognition.common.shiro.UserRealm;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    /**
     * EhCache 构造注入
     */
    private final EhCacheManager ehCacheManager;
    /**
     * Redis 构造注入
     */
    private final RedisManager redisManager;

    /**
     * @param ehCacheManager  EhCacheConfig(ehcache配置，启动)
     * @param redisManager    Re
     */
    @Autowired
    public SystemConfig(EhCacheManager ehCacheManager, RedisManager redisManager) {
        this.ehCacheManager = ehCacheManager;
        this.redisManager = redisManager;
    }

    /**
     * 设置安全管理器放入缓存
     *
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        //声明用户权限
        UserRealm userReal = new UserRealm();
        //会话权限
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm
        securityManager.setRealm(userReal);
        //设置缓存实现方法，使用redis或者使用ehCache
        if (Constant.CACHE_TYPE_REDIS.equals(cacheType)) {
            securityManager.setCacheManager(rediscacheManager());
        } else {
            securityManager.setCacheManager(ehCacheManager);
        }
        return securityManager;
    }


    /**
     * cacheManager 缓存 （redis实现）
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    private RedisCacheManager rediscacheManager() {
        //初始化redis缓存对象
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        //将redis对象放入操作对象返回，完成redis初始化
        redisCacheManager.setRedisManager(redisManager);
        return redisCacheManager;
    }


    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     */
    @Bean
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
    @Bean
    public SessionDAO sessionDAO() {
        if (Constant.CACHE_TYPE_REDIS.equals(cacheType)) {
            return redisSessionDAO();
        } else {
            return new MemorySessionDAO();
        }
    }

}
