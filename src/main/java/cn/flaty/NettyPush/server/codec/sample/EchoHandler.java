package cn.flaty.NettyPush.server.codec.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class EchoHandler extends SimpleChannelInboundHandler<String> {

	
	private static Logger log = LoggerFactory.getLogger(EchoHandler.class);
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg)
			throws Exception {
		log.info("收到:{}", msg);
		
	}





}
