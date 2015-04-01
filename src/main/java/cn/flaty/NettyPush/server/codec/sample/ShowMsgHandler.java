package cn.flaty.NettyPush.server.codec.sample;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShowMsgHandler extends ChannelInboundHandlerAdapter {

	private static Logger log = LoggerFactory.getLogger(ShowMsgHandler.class);

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ByteBuf buf = (ByteBuf) msg;
		StringBuilder sb = new StringBuilder();
		try {
			while (buf.isReadable()) { 
				sb.append((char) buf.readByte());
			}
		} finally {
			ReferenceCountUtil.release(msg); 
		}
		log.info("收到: {}",sb.toString());
		
	}

}
