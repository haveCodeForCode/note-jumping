package com.root.cognition.system.config;

//slf4j日志
import com.root.cognition.system.dao.shiro.UserRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//设置注解
import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;



@Configuration
public class shiroConfigtion {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.cache.type}")
    private String cacheType ;
    @Value("${server.session-timeout}")
    private int tomcatTimeout;

    /**
     * 获取Shiro日志
     */
    private static final Logger shiroLogger =  LoggerFactory.getLogger(shiroConfigtion.class);

    /**
     * 添加shiro 自主注入bean的生命周期管理
     * @return
     */
    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 页面 ShiroDialect 注入 为了在thymeleaf里使用shiro的标签的bean
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }


    @Bean
    public SecurityManager securityManager(){
        UserRealm = new UserRealm();
        //声明网页
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm
        securityManager.setRealm(userReal,);
    }



    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        return  shiroFilterFactoryBean;
    }


}
