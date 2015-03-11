package cn.flaty.NettyPush.server.codec.push;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import cn.flaty.NettyPush.core.ClientDispacherService;
import cn.flaty.NettyPush.server.conn.NettyConnection;
import cn.flaty.NettyPush.utils.beanFactoryUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

public class DeserializeHandler extends SimpleChannelInboundHandler<String> {
	
	
	private Logger log = LoggerFactory.getLogger(DeserializeHandler.class);

	private ClientDispacherService deserialize;


	public DeserializeHandler() {
		super();
		deserialize = beanFactoryUtils.getClientDispacherService();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg)
			throws Exception {
		log.info(String.valueOf(msg.length()));
		log.info(msg);
		NettyConnection conn = new NettyConnection(ctx);
		deserialize.dispacher(conn, msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		log.error(MessageFormat.format("{0} {1}", ctx.channel().remoteAddress(),cause.getMessage()));
	}

}