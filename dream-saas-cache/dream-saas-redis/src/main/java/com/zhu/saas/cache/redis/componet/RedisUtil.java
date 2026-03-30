package com.zhu.saas.cache.redis.componet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * ClassName:    RedisUtil
 * Package:    com.zhu.saas.gateway.util
 * Description:
 * Datetime:    2026/3/18   16:29
 * Author:   朱殿辉
 */
@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate redisTemplate;
    private final String SETNX_EXPIRE_SCRIPT = "if redis.call('setnx', KEYS[1], ARGV[1]) == 1 then\nreturn redis.call('expire', KEYS[1], ARGV[2]);\nend\nreturn 0;";

    public long del(final String... keys) {
        return (Long)this.redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                long result = 0L;

                for(int i = 0; i < keys.length; ++i) {
                    result = connection.del(new byte[][]{keys[i].getBytes()});
                }

                return result;
            }
        });
    }

    public Long getExpireTime(final String key) {
        return this.redisTemplate.opsForValue().getOperations().getExpire(key);
    }

    public boolean set(final String key, Object value) {
        boolean result = false;

        try {
            ValueOperations operations = this.redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;

        try {
            ValueOperations operations = this.redisTemplate.opsForValue();
            operations.set(key, value, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean exists(final String key) {
        return this.redisTemplate.hasKey(key);
    }

    public Object getSet(final String key, Object value) {
        Object result = null;
        ValueOperations operations = this.redisTemplate.opsForValue();
        result = operations.getAndSet(key, value);
        return result;
    }

    public void addSet(String key, String value) {
        this.redisTemplate.opsForSet().add(key, new Object[]{value});
    }

    public void removeSet(String key, String value) {
        this.redisTemplate.opsForSet().remove(key, new Object[]{value});
    }

    public Set<String> getSpecifyKey(String key) {
        String prefix = key + "*";
        return this.redisTemplate.keys(prefix);
    }

    public Set<String> members(String key) {
        return this.redisTemplate.opsForSet().members(key);
    }

    public boolean setZset(final String key, Object value, double score) {
        boolean result = false;

        try {
            ZSetOperations zSetOperations = this.redisTemplate.opsForZSet();
            zSetOperations.add(key, value, score);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public Set<Object> getZset(final String key) {
        ZSetOperations zSetOperations = this.redisTemplate.opsForZSet();
        Set<Object> range = zSetOperations.range(key, 0L, -1L);
        return range;
    }

    public Set<Object> getZset(final String key, final long start, final long stop) {
        ZSetOperations zSetOperations = this.redisTemplate.opsForZSet();
        Set<Object> range = zSetOperations.range(key, start, stop);
        return range;
    }

    public boolean removeZset(final String key, Object value) {
        boolean result = false;

        try {
            ZSetOperations zSetOperations = this.redisTemplate.opsForZSet();
            zSetOperations.remove(key, new Object[]{value});
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public Object get(final String key) {
        Object result = null;
        ValueOperations operations = this.redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    public void remove(final String... keys) {
        this.redisTemplate.delete(keys);
    }

    public void removePattern(final String pattern) {
        Set<Serializable> keys = this.redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            this.redisTemplate.delete(keys);
        }

    }

    public void remove(final String key) {
        if (this.exists(key)) {
            this.redisTemplate.delete(key);
        }

    }

    public boolean setListLeft(final String key, Object value) {
        boolean result = false;

        try {
            ListOperations operations = this.redisTemplate.opsForList();
            operations.leftPush(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean setListRight(final String key, Object value) {
        boolean result = false;

        try {
            ListOperations operations = this.redisTemplate.opsForList();
            operations.rightPush(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public Object getListLeft(final String key) {
        Object result = null;

        try {
            ListOperations operations = this.redisTemplate.opsForList();
            result = operations.leftPop(key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public Object getListRight(final String key) {
        Object result = null;

        try {
            ListOperations operations = this.redisTemplate.opsForList();
            result = operations.rightPop(key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean setHash(final String key, String hashKay, Object value) {
        boolean result = false;

        try {
            HashOperations operations = this.redisTemplate.opsForHash();
            operations.put(key, hashKay, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean setHash(final String key, Map<Object, Object> value) {
        boolean result = false;

        try {
            HashOperations operations = this.redisTemplate.opsForHash();
            operations.putAll(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public Object getHash(final String key) {
        Object result = null;

        try {
            HashOperations operations = this.redisTemplate.opsForHash();
            result = operations.entries(key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean existsHash(final String key, final String hashKey) {
        return this.redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    public void deleteHash(final String key, final Object... hashKeys) {
        this.redisTemplate.opsForHash().delete(key, hashKeys);
    }

    public boolean setnxAndExpire(String key, Object value, int seconds) {
        RedisScript<Long> script = new DefaultRedisScript("if redis.call('setnx', KEYS[1], ARGV[1]) == 1 then\nreturn redis.call('expire', KEYS[1], ARGV[2]);\nend\nreturn 0;", Long.class);
        Long result = (Long)this.redisTemplate.execute(script, Collections.singletonList(key), new Object[]{value, seconds});
        return result > 0L;
    }

    public boolean setNX(String key, int timeout) {
        Boolean notExists = this.redisTemplate.getConnectionFactory().getConnection().setNX(key.getBytes(), new byte[0]);
        this.redisTemplate.expire(key, (long)timeout, TimeUnit.SECONDS);
        return notExists != null ? notExists : false;
    }

    public Long incr(String key, long delta) {
        return this.redisTemplate.opsForValue().increment(key, delta);
    }

    public Double incr(String key, double delta) {
        return this.redisTemplate.opsForValue().increment(key, delta);
    }

    public Long incr(String key, String o, long delta) {
        return this.redisTemplate.opsForHash().increment(key, o, delta);
    }

    public Long getExpire(String key) {
        return this.redisTemplate.getExpire(key);
    }

    public Boolean expire(String key, long timeout, TimeUnit unit) {
        return this.redisTemplate.expire(key, timeout, unit);
    }

    public Number execute(RedisScript<Number> script, List<String> keys, Object value, int seconds) {
        Number no = (Number)this.redisTemplate.execute(script, keys, new Object[]{value, seconds});
        return no;
    }
}
