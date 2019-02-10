package com.root.cognition.common.redis;

import com.root.cognition.system.config.RedisConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

/**
 * redis 操作
 * @author LineInkBook
 */
public class RedisManager {

    private String redisHost;

    private String redisPassword;

    private int redisPort;

    private int redisTimeout;

    private static JedisPool jedisPool = null;

    /**
     * 初始化截止redis时间
     */
    private int expire = 1;

    /**
     * 初始化方法
     */
    public void init() {
        if (jedisPool == null) {
            if (redisPassword != null && !"".equals(redisPassword)) {
                jedisPool = new JedisPool(new JedisPoolConfig(), redisHost, redisPort, redisTimeout, redisPassword);
            } else if (redisTimeout != 0) {
                jedisPool = new JedisPool(new JedisPoolConfig(), redisHost, redisPort, redisTimeout);
            } else {
                jedisPool = new JedisPool(new JedisPoolConfig(), redisHost, redisPort);
            }

        }
    }


    /**
     * 从redis中获得value
     *
     * @param key
     * @return
     */
    public byte[] redisGet(byte[] key) {
        byte[] value = null;
        Jedis jedis = jedisPool.getResource();
        try {
            value = jedis.get(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    /**
     * key ，value放入redis
     *
     * @param key
     * @param value
     * @return
     */
    public byte[] redisSet(byte[] key, byte[] value) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    /**
     * key,value,expire（存在时间）放入redis
     *
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public byte[] redisSet(byte[] key, byte[] value, int expire) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, value);
            if (expire != 0) {
                jedis.expire(key, expire);
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    /**
     * 根据key删除redis
     *
     * @param key
     */
    public void redisDel(byte[] key) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.del(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 清空reids
     */
    public void redisFlushDB() {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.flushDB();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 获取缓存池中数量
     */
    public Long redisDbSize() {
        Long dbSize = 0L;
        Jedis jedis = jedisPool.getResource();
        try {
            dbSize = jedis.dbSize();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return dbSize;
    }

    /**
     * keys
     *
     * @param pattern
     * @return
     */
    public Set<byte[]> redisKeys(String pattern) {
        Set<byte[]> keys = null;
        Jedis jedis = jedisPool.getResource();
        try {
            keys = jedis.keys(pattern.getBytes());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return keys;
    }


    public int getRedisTimeout() {
        return redisTimeout;
    }

    public void setRedisTimeout(int redisTimeout) {
        this.redisTimeout = redisTimeout;
    }

    public String getRedisHost() {
        return redisHost;
    }

    public void setRedisHost(String redisHost) {
        this.redisHost = redisHost;
    }

    public String getRedisPassword() {
        return redisPassword;
    }

    public void setRedisPassword(String redisPassword) {
        this.redisPassword = redisPassword;
    }

    public int getRedisPort() {
        return redisPort;
    }

    public void setRedisPort(int redisPort) {
        this.redisPort = redisPort;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }
}
