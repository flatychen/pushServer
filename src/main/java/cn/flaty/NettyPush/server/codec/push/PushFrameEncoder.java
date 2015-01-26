package cn.flaty.NettyPush.server.codec.push;

import java.util.Arrays;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import cn.flaty.NettyPush.server.frame.FrameHead;
import cn.flaty.NettyPush.server.frame.SimplePushOutFrame;

public class PushFrameEncoder extends MessageToByteEncoder<String> {
	
	private FrameHead frameHead;
	
	public PushFrameEncoder(FrameHead frameHead) {
		super();
		this.frameHead = frameHead;
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out)
			throws Exception {
		SimplePushOutFrame frame = new SimplePushOutFrame(frameHead, msg.getBytes());
		out.writeBytes(frame.getHead());
		out.writeBytes(frame.getBody());
	}




}
