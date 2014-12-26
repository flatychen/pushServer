package base;

import java.util.Arrays;

import org.junit.Test;

import cn.flaty.NettyPush.model.GenericMessage;
import cn.flaty.NettyPush.utils.FastJsonUtils;

public class FastjsonUtilsTest {
	
	
	@Test
	public void Test(){
		String s = "Selector（选择器）是Java NIO中能够检测一到多个NIO通道，并能够知晓通道是否为诸如读写事件做好准备的组件。这样，一个单独的线程可以管理多个channel，从而管理多个网络连接。";
		GenericMessage msg = new GenericMessage();
		msg.setCommond(5);
		msg.setMessage(s);
		
		String json = FastJsonUtils.toJsonString(msg);
		byte [] b = json.getBytes();
		System.out.println(json.length());
		System.out.println(Arrays.toString(b));
		System.out.println(b.length);
		
	}

}
