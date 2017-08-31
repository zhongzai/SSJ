package com.xiaomai.supershopowner.dao;

import java.io.Serializable;  

import org.springframework.data.redis.core.RedisTemplate;  
  
public interface RedisDAO {  
  
    public abstract void sendMessage(String channel, Serializable message);  
  
    public abstract RedisTemplate getRedisTemplate();  
  
    public abstract void setRedisTemplate(RedisTemplate redisTemplate);  
  
}  