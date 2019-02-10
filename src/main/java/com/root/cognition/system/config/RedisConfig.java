package com.root.cognition.system.config;

import com.root.cognition.common.redis.RedisManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author LineInkBook
 */
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.password}")
    private String redisPassword;
    @Value("${spring.redis.port}")
    private int redisPort;
    /**
     * 时间内没有连接上就断开连接
     */
    @Value("${spring.redis.timeout}")
    private int redisTimeout;

    /**
     * redisManager初始化设置
     *
     * @return
     */
    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setRedisHost(redisHost);
        redisManager.setRedisPort(redisPort);
        // 配置缓存过期时间
        redisManager.setExpire(1800);
        redisManager.setRedisTimeout(redisTimeout);
        redisManager.setRedisPassword(redisPassword);
        return redisManager;
    }


}
