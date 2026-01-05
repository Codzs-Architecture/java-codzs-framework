package com.codzs.utility;

import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

public class RedisUtils {
    public static Cache getRedisCache(RedisConnectionFactory redisConnectionFactory, String cacheKey) {
        RedisCacheWriter cacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);

        // Create an instance of GenericJackson2JsonRedisSerializer
        JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer();

        return RedisCacheManager.builder()
                .cacheWriter(cacheWriter)
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig()
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer)))
                .build()
                .getCache(cacheKey);
    }
}
