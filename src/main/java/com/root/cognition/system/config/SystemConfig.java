package com.root.cognition.system.config;


import com.root.cognition.common.redis.RedisCacheManager;
import com.root.cognition.common.redis.RedisManager;
import com.root.cognition.common.redis.RedisSessionDAO;
import com.root.cognition.common.shiro.UserRealm;
import com.root.cognition.until.DataDic;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * 系统基本设置
 * @author LineInkBook
 */
@Configuration
public class SystemConfig {

    /**
     * 使用类型缓存
     */
    @Value("${spring.cache.type}")
    private String cacheType ;

    @Autowired
    private final EhCacheManager ehCacheManager;

    public SystemConfig(EhCacheManager ehCacheManager) {
        this.ehCacheManager = ehCacheManager;
    }


    /**
     * Shiro拦截器配置
     * @param securityManager
     * @return
     */
    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/login","anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/toGuide","anon");
//        filterChainDefinitionMap.put("/docs/**", "anon");
//        filterChainDefinitionMap.put("/druid/**", "anon");
//        filterChainDefinitionMap.put("/upload/**", "anon");
//        filterChainDefinitionMap.put("/files/**", "anon");
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/", "anon");
//        filterChainDefinitionMap.put("/blog", "anon");
//        filterChainDefinitionMap.put("/blog/open/**", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return  shiroFilterFactoryBean;
    }


    /**
     * 设置安全管理器放入缓存
     * @return
     */
    @Bean
    public SecurityManager securityManager(){
        //声明用户权限
        UserRealm userReal = new UserRealm();
        //会话权限
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm
        securityManager.setRealm(userReal);
        //设置缓存实现方法，使用redis或者使用ehCache
        if(DataDic.CAHE_TYPE_REDIS.equals(cacheType)){
            securityManager.setCacheManager(rediscacheManager());
        }else{
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
    public RedisCacheManager rediscacheManager() {
        //初始化redis对象
        RedisManager redisManager = new RedisManager();
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
     * @return
     */
    @Bean
    public SessionDAO sessionDAO() {
        if (DataDic.CAHE_TYPE_REDIS.equals(cacheType)) {
            return redisSessionDAO();
        } else {
            return new MemorySessionDAO();
        }
    }

}
