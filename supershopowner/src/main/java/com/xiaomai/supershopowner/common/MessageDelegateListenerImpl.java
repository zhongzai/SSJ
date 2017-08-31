package com.xiaomai.supershopowner.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
  
/** 
 * 接收消息的Listener,用于接收订阅到的消息.  
 * @author Administrator 
 * 
 */  
public class MessageDelegateListenerImpl extends MessageListenerAdapter {  
	
	@Autowired
	ChannelTopic channelTopic;
  
    public void onMessage(Message message,byte[] pattern) {  
       try{  
            System.out.println("接受数据"+new String(message.getBody())+channelTopic.getTopic());  
        }catch(Exception e){
        	
        }  
    }  
}  
