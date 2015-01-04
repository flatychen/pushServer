package cn.flaty.NettyPush.server.codec.push;

import java.util.Arrays;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import cn.flaty.NettyPush.server.frame.FrameHead;

public class PushFrameEncoder extends MessageToByteEncoder<String> {
	
	private FrameHead frameHead;
	
	
	private byte [] head;
	
	public PushFrameEncoder(FrameHead frameHead) {
		super();
		this.frameHead = frameHead;
		this.head = new byte[frameHead.headLength()];
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out)
			throws Exception {
		initHead();
		out.writeBytes(head);
		out.writeBytes(msg.getBytes());
	}

	private void initHead() {
		head[0] = 1;
		
	}



}
