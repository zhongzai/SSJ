package supershopowner;

import org.junit.Test;

import com.xiaomai.supershopowner.common.AndroidBroadcast;
import com.xiaomai.supershopowner.common.AndroidNotification;
import com.xiaomai.supershopowner.common.PushClient;

public class UmTest {
	private String appkey = "599bc9663eae257b24000634";
	private String appMasterSecret = "ma1zrw7mxu2f9z7ageh7lugfsqanp84n";
	private String timestamp = "3746474";
	private PushClient client = new PushClient();
	
	public UmTest(String key, String secret) {
		try {
			appkey = key;
			appMasterSecret = secret;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	@Test
	public void sendAndroidBroadcast() throws Exception {
		
		AndroidBroadcast broadcast = new AndroidBroadcast(appkey,appMasterSecret);
		broadcast.setTicker( "Android broadcast ticker");
		broadcast.setTitle(  "中文的title");
		broadcast.setText(   "Android broadcast text");
		broadcast.goAppAfterOpen();
		broadcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		
		broadcast.setProductionMode();
		broadcast.setExtraField("test", "helloworld");
		client.send(broadcast);
	}

}
