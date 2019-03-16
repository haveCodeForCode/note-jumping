package com.root.cognition.system.config;

import net.sf.ehcache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    @Bean("cacheManager2")
    CacheManager cacheManager() {
        return CacheManager.create();
    }
}
