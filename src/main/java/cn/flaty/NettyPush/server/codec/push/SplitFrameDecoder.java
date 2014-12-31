package cn.flaty.NettyPush.server.codec.push;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import cn.flaty.NettyPush.server.frame.FrameHead;
import cn.flaty.NettyPush.server.frame.SimplePushHead;
import cn.flaty.NettyPush.utils.beanFactoryUtils;

/**
 * @author flaty
 * 
 */
public class SplitFrameDecoder  extends LengthFieldBasedFrameDecoder {

	
	public SplitFrameDecoder(FrameHead frameHead) {
		super(frameHead.maxLength(), 0, frameHead.byteLength(), frameHead.headLength(),
				frameHead.byteLength());
	}

}
