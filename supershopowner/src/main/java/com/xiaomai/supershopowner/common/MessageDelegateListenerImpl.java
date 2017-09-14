package com.xiaomai.supershopowner.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
  
/** 
 * 接收消息的Listener,用于接收订阅到的消息.  
 * @author Administrator 
 * 
 */  
public class MessageDelegateListenerImpl extends MessageListenerAdapter {  
	
	private String appkey = "59ae1720310c934edc00020c";
	private String appMasterSecret = "kz7qxboshsjarffugscczowgetndo4fm";
	private PushClient client = new PushClient();
	
	@Autowired
	ChannelTopic channelTopic;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	RedisTemplate redisTemplate;
  
    public void onMessage(Message message,byte[] pattern) {  
       try{  
    	   
    	   AndroidBroadcast broadcast = new AndroidBroadcast(appkey,appMasterSecret);
	   		broadcast.setTicker("buy");
	   		broadcast.setTitle("顾客买单");
	   		broadcast.setCustomField( (String)redisTemplate.getValueSerializer().deserialize(message.getBody()));
	   		broadcast.goAppAfterOpen();
	   		broadcast.setDisplayType(AndroidNotification.DisplayType.MESSAGE);
	   		
	   		broadcast.setProductionMode();
	   		client.send(broadcast);
            System.out.println((String)redisTemplate.getValueSerializer().deserialize(message.getBody()));  
        }catch(Exception e){
        	e.printStackTrace();
        }  
    }  
}  
