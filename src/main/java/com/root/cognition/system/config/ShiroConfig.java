package com.root.cognition.system.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.root.cognition.common.config.Constant;
import com.root.cognition.common.redis.RedisCacheManager;
import com.root.cognition.common.redis.RedisManager;
import com.root.cognition.common.redis.RedisSessionDAO;
import com.root.cognition.common.shiro.BDSessionListener;
import com.root.cognition.common.shiro.UserRealm;
import com.root.cognition.system.service.UserService;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;


/**
 * Shiro 设置
 *
 * @author LineInkBook
 */
@Configuration
public class ShiroConfig {


    @Value("${spring.cache.type}")
    private String cacheType;
    @Value("${server.servlet.session.timeout}")
    private int tomcatTimeout;

    /**
     * Shiro拦截器配置
     *
     * @param securityManager
     * @return
     */
    @Bean(name = "shiroFilterFactory")
    ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager,UserService userService) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //登录地址
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/toGuide", "anon");
        filterChainDefinitionMap.put("/getVerify", "anon");
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/**", "authc");
//        filterChainDefinitionMap.put("/docs/**", "anon");
//        filterChainDefinitionMap.put("/druid/**", "anon");
//        filterChainDefinitionMap.put("/upload/**", "anon");
//        filterChainDefinitionMap.put("/files/**", "anon");
//        filterChainDefinitionMap.put("/blog", "anon");
//        filterChainDefinitionMap.put("/blog/open/**", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


//    /**
//     * 注册shiro的Filter，拦截请求
//     */
//    @Bean
//    public FilterRegistrationBean<Filter> filterRegistrationBean(SecurityManager securityManager, UserService userService) throws Exception{
//        FilterRegistrationBean<Filter> filterRegistration = new FilterRegistrationBean<Filter>();
//        filterRegistration.setFilter((Filter) shiroFilterFactoryBean(securityManager, userService));
//        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
//        filterRegistration.setAsyncSupported(true);
//        filterRegistration.setEnabled(true);
//        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
//
//        return filterRegistration;
//    }


//    /**
//     * 禁用session, 不保存用户登录状态。保证每次请求都重新认证。
//     * 需要注意的是，如果用户代码里调用Subject.getSession()还是可以用session，如果要完全禁用，要配合下面的noSessionCreation的Filter来实现
//     */
//    @Bean
//    protected SessionStorageEvaluator sessionStorageEvaluator(){
//        DefaultWebSessionStorageEvaluator sessionStorageEvaluator = new DefaultWebSessionStorageEvaluator();
//        sessionStorageEvaluator.setSessionStorageEnabled(false);
//        return sessionStorageEvaluator;
//    }

//    /**
//     * 初始化Authenticator
//     */
//    @Bean
//    public Authenticator authenticator(UserService userService) {
//        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
//        //设置两个Realm，一个用于用户登录验证和访问权限获取；一个用于jwt token的认证
//        authenticator.setRealms(Arrays.asList(jwtShiroRealm(userService), dbShiroRealm(userService)));
//        //设置多个realm认证策略，一个成功即跳过其它的
//        authenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());
//        return authenticator;
//    }


//    /**
//     * 用于用户名密码登录时认证的realm
//     */
//    @Bean("dbRealm")
//    public Realm dbShiroRealm(UserService userService) {
//        DbUserShiroRealm myShiroRealm = new DbUserShiroRealm(userService);
//        return myShiroRealm;
//    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

//************************session**********************************

    private final RedisConfig redisConfig;

    private final RedisManager redisManager;

    @Autowired
    public ShiroConfig(RedisConfig redisConfig, RedisManager redisManager) {
        this.redisConfig = redisConfig;
        this.redisManager = redisConfig.redisManager();
    }


    @Bean
    UserRealm userRealm() {
        return new UserRealm();
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm.
        securityManager.setRealm(userRealm());
        // 自定义缓存实现 使用redis
        if (Constant.CACHE_TYPE_REDIS.equals(cacheType)) {
            securityManager.setCacheManager(rediscacheManager());
        } else {
            securityManager.setCacheManager(ehCacheManager());
        }
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    public RedisCacheManager rediscacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
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
        redisSessionDAO.setRedisManager(redisManager);
        return redisSessionDAO;
    }

    /**
     * 添加shiro 自主注入bean的生命周期管理
     *
     * @return
     */
    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 页面 ShiroDialect 注入 为了在thymeleaf里使用shiro的标签的bean
     *
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    @Bean("cacheManager")
    public EhCacheManager ehCacheManager() {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManager(CacheManager.create());
        return em;
    }


    @Bean
    public SessionDAO sessionDAO() {
        if (Constant.CACHE_TYPE_REDIS.equals(cacheType)) {
            return redisSessionDAO();
        } else {
            return new MemorySessionDAO();
        }
    }

    /**
     * shiro session的管理
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(tomcatTimeout * 1000);
        sessionManager.setSessionDAO(sessionDAO());
        Collection<SessionListener> listeners = new ArrayList<SessionListener>();
        listeners.add(new BDSessionListener());
        sessionManager.setSessionListeners(listeners);
        return sessionManager;
    }

}

