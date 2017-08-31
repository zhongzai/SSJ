package com.xiaomai.supershopowner.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;



@Repository
public class TopicMessageListener implements  MessageListener {  
	@Autowired
	private RedisTemplate redisTemplate;  
    
    public void setRedisTemplate(RedisTemplate redisTemplate) {  
        this.redisTemplate = redisTemplate;  
    } 
	@Override  
    public void onMessage(Message message, byte[] pattern) {  
        byte[] body = message.getBody();//请使用valueSerializer  
        byte[] channel = message.getChannel();  
        String itemValue = (String)redisTemplate.getValueSerializer().deserialize(body);  
        String topic = (String)redisTemplate.getStringSerializer().deserialize(channel);  
        System.out.println("message" + topic); 
        System.out.println("message" + itemValue); 
    }  
}  
