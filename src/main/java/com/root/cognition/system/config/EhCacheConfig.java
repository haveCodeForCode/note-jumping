package com.root.cognition.system.config;


import net.sf.ehcache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LineInkBook
 */
@Configuration
public class EhCacheConfig {

    /**
     * ehcache缓存初始化设置
     *
     * @return
     */
    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManager(cacheManager());
        return ehCacheManager;
    }

//    @Bean("cacheManager2")
//    CacheManager cacheManager() {
//        return CacheManager.create("zero");
//    }

    @Bean("cacheManagerSet")
    public CacheManager cacheManager() {
//        CacheManager cacheManager = new CacheManager();
//        cacheManager.addCache("zero");
//        URL url = "config/ehcache.xml";
//        return new CacheManager();
        return CacheManager.create();
    }
}
