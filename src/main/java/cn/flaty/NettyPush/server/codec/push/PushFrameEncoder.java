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
		
		
		System.out.println(out);
		
		
		initHead();
		
		out.writeBytes(head);
		System.out.println(Arrays.toString(msg.getBytes()));
		System.out.println(Arrays.toString("ab".getBytes()));
		out.writeBytes(msg.getBytes());
		System.out.println(out);
	}

	private void initHead() {
		head[0] = 1;
		
	}



}
