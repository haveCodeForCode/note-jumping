package com.root.cognition.common.redis;

import com.root.cognition.system.config.RedisConfig;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 *
 *
 * @author LineInkBook
 */
public class RedisCacheManager implements CacheManager {
    /**
     * 获取类日志
     */
    private static final Logger logger = LoggerFactory.getLogger(RedisCacheManager.class);

    /**
     * 获取redis设置
     */
    @Resource
    private RedisConfig redisConfig;

    /**
     * 按名称映射快速查找
     */
    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();

    /**
     * 声明redis
     */
    private RedisManager redisManager;

    /**
     *  声明 redis 缓存的名称
     */
    private String keyPrefix = "shiro_redis_cache:";


    public RedisManager getRedisManager() {
        return redisManager;
    }

    public void setRedisManager(RedisManager redisManager) {
        this.redisManager = redisManager;
    }


    /**
     * Returns the Redis session keys
     * prefix.
     * @return The prefix
     */
    public String getKeyPrefix() {
        return keyPrefix;
    }

    /**
     * Sets the Redis sessions key
     * prefix.
     * @param keyPrefix The prefix
     */
    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }


    /**
     * 获取缓存内容
     * @param name
     * @param <K>
     * @param <V>
     * @return
     * @throws CacheException
     */
    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        logger.debug("获取名称为: " + name + " 的RedisCache实例");

        Cache c = caches.get(name);

        if (c == null) {

            // 初始化RedisManager实例
            redisManager.init();

            // 新建缓存实例
            c = new RedisCache<K, V>(redisManager, keyPrefix);

            // add it to the cache collection
            caches.put(name, c);
        }
        return c;
    }


}
