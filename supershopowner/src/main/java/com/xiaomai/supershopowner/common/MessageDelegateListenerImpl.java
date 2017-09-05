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
	
	private String appkey = "599bc9663eae257b24000634";
	private String appMasterSecret = "ma1zrw7mxu2f9z7ageh7lugfsqanp84n";
	private PushClient client = new PushClient();
	
	@Autowired
	ChannelTopic channelTopic;
  
    public void onMessage(Message message,byte[] pattern) {  
       try{  
    	   
    	   AndroidBroadcast broadcast = new AndroidBroadcast(appkey,appMasterSecret);
	   		broadcast.setTicker("buy");
	   		broadcast.setTitle("顾客买单");
	   		broadcast.setCustomField(new String(message.getBody()));
	   		broadcast.goAppAfterOpen();
	   		broadcast.setDisplayType(AndroidNotification.DisplayType.MESSAGE);
	   		
	   		broadcast.setProductionMode();
	   		client.send(broadcast);
            System.out.println("接受数据"+new String(message.getBody()));  
        }catch(Exception e){
        	
        }  
    }  
}  
