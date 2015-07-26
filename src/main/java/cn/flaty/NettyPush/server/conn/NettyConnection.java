package cn.flaty.NettyPush.server.conn;


import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import cn.flaty.NettyPush.utils.AssertUtils;

/**
 * 
 * TCP 连接
 * 
 * @author flatychen
 * 
 */
public class NettyConnection {
	
	
	private Logger log = org.slf4j.LoggerFactory.getLogger(NettyConnection.class);

	private ChannelHandlerContext context;

	public NettyConnection(ChannelHandlerContext context) {
		super();
		AssertUtils.notNull(context, "----> context 不能为空");
		this.context = context;
	}

	public ChannelFuture writeAndFlush(String s) {
		log.info("send:{}",s);
		return context.writeAndFlush(s);
	}

}
