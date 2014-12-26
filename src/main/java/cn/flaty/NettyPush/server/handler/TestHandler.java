package cn.flaty.NettyPush.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.flaty.NettyPush.server.codec.push.DeserializeHandler;
import cn.flaty.NettyPush.server.conn.NettyConnection;
import cn.flaty.NettyPush.services.ClientDispacherService;
import cn.flaty.NettyPush.utils.beanFactoryUtils;

@Sharable
public class TestHandler extends ChannelInboundHandlerAdapter {

	private ClientDispacherService deserialize;

	private Logger log = LoggerFactory.getLogger(DeserializeHandler.class);

	public TestHandler() {
		super();
		deserialize = beanFactoryUtils.getClientDispacherService();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		NettyConnection conn = new NettyConnection(ctx);
		deserialize.dispacher(conn, "");
	}


}
