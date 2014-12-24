package cn.flaty.NettyPush.server.codec.push;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import cn.flaty.NettyPush.server.conn.NettyConnection;
import cn.flaty.NettyPush.services.DeserializeService;
import cn.flaty.NettyPush.utils.beanFactoryUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

public class DeserializeHandler extends SimpleChannelInboundHandler<String> {
	
	private DeserializeService deserialize;
	
	private Logger log = LoggerFactory.getLogger(DeserializeHandler.class);

	public DeserializeHandler() {
		super();
		deserialize = beanFactoryUtils.getDeserializeService();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg)
			throws Exception {
		log.info(msg);
		NettyConnection conn = new NettyConnection(ctx);
		deserialize.dispacher(conn, msg);
	}

	
	
}