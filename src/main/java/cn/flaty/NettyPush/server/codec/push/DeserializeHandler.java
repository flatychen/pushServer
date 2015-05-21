package cn.flaty.NettyPush.server.codec.push;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.flaty.NettyPush.server.conn.NettyConnection;
import cn.flaty.NettyPush.services.ClientDispacherService;
import cn.flaty.NettyPush.utils.beanFactoryUtils;

/**
 * 
 * 业务处理.
 * 
 * @author flatychen
 * 
 */
public class DeserializeHandler extends SimpleChannelInboundHandler<String> {

	private ClientDispacherService deserialize;

	private Logger log = LoggerFactory.getLogger(DeserializeHandler.class);

	public DeserializeHandler() {
		super();
		deserialize = beanFactoryUtils.getClientDispacherService();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg)
			throws Exception {
		log.info("receive msg:{}", msg);
		// 分发
		NettyConnection conn = new NettyConnection(ctx);
		deserialize.dispacher(conn, msg);
	}

}