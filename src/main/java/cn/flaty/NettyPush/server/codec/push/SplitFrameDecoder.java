package cn.flaty.NettyPush.server.codec.push;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import cn.flaty.NettyPush.server.frame.PushFrame;

/**
 * @author flaty
 *
 */
public class SplitFrameDecoder  extends LengthFieldBasedFrameDecoder{
	
	
	
	public SplitFrameDecoder() {
		super(Integer.MAX_VALUE, 0, PushFrame.head_length_bytes, PushFrame.head_bytes - PushFrame.head_length_bytes , PushFrame.head_length_bytes);
	}

}
