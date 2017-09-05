package supershopowner;

import org.junit.Test;

import com.xiaomai.supershopowner.common.AndroidBroadcast;
import com.xiaomai.supershopowner.common.AndroidNotification;
import com.xiaomai.supershopowner.common.PushClient;

public class UmTest {
	private String appkey = "599bc9663eae257b24000634";
	private String appMasterSecret = "ma1zrw7mxu2f9z7ageh7lugfsqanp84n";
	private String timestamp = null;
	private PushClient client = new PushClient();

	@Test
	public void sendAndroidBroadcast() throws Exception {
		
		AndroidBroadcast broadcast = new AndroidBroadcast(appkey,appMasterSecret);
		broadcast.setTicker( "Android broadcast ticker");
		broadcast.setTitle(  "中文的title");
		broadcast.setCustomField("{\"shopCode\":\"XM0001\",\"orderCode\":\"21XM0001253456576683425\",\"payType\":\"1\",\"payTime\":\"1503999234486\",\"price\":\"101.00\"}");
		broadcast.goAppAfterOpen();
		broadcast.setDisplayType(AndroidNotification.DisplayType.MESSAGE);
		
		broadcast.setProductionMode();
		broadcast.setExtraField("test", "helloworld");
		client.send(broadcast);
	}

}
