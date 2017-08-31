package supershopowner;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xiaomai.supershopowner.common.MessageDelegateListenerImpl;
  
  
public class TestRedisConsumer {  
    private MessageDelegateListenerImpl messageDelegateListener=null;  
  
    @Before
    public void setUp() throws Exception {  
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-redis.xml");  
        messageDelegateListener = (MessageDelegateListenerImpl) applicationContext.getBean("messageDelegateListener");  
    }  
  
    public static void main(String[] args) {  
        new ClassPathXmlApplicationContext("spring-redis.xml");  
        System.out.println("消费者1");  
        while (true) { //这里是一个死循环,目的就是让进程不退出,用于接收发布的消息  
            try {  
                Thread.sleep(3000);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
}  
