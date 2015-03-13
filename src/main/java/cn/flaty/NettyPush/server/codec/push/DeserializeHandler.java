package cn.flaty.NettyPush.server.codec.push;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.flaty.NettyPush.core.ClientDispacherService;
import cn.flaty.NettyPush.server.conn.NettyConnection;
import cn.flaty.NettyPush.utils.beanFactoryUtils;

/**
 * 
 * 接收包处理
 * @author flatychen
 *
 */
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
		log.info("receive packet[长度:{},内容:{}]",String.valueOf(msg.length()),msg);
		NettyConnection conn = new NettyConnection(ctx);
		deserialize.dispacher(conn, msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		log.error("{} {}", ctx.channel().remoteAddress(),cause.getMessage());
	}

}