package cn.flaty.NettyPush.server.codec.push;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import cn.flaty.NettyPush.server.frame.FrameHead;

/**
 * @author flaty
 *
 */
public class SplitFrameDecoder  extends LengthFieldBasedFrameDecoder{
	
	
	
	public SplitFrameDecoder() {
		super(Integer.MAX_VALUE, 0, FrameHead.head_length_bytes, FrameHead.head_bytes - FrameHead.head_length_bytes , FrameHead.head_length_bytes);
	}

}
