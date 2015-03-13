package cn.flaty.NettyPush.server.handler;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.InetSocketAddress;
import java.text.MessageFormat;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.flaty.NettyPush.utils.InetSocketUtils;

public class WatchInBaseBoundHandler extends ChannelInboundHandlerAdapter {

	private Logger log = LoggerFactory.getLogger(WatchInBaseBoundHandler.class);

	private AtomicInteger threads = new AtomicInteger(0);

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		InetSocketAddress socket = this.getClientSocket(ctx);
		log.info(MessageFormat.format("one client connected; host:{1}", 1,
				InetSocketUtils.getSocketAddress(socket)));
		log.info("the client counts is:" + (threads.getAndIncrement() + 1)
				+ "~~");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		log.warn(MessageFormat.format("-->与客户端{1}断开连接; msg:{3}", 1,
				InetSocketUtils.getSocketAddress(this.getClientSocket(ctx)), 3,
				cause.getMessage()));
	}

	public final InetSocketAddress getClientSocket(ChannelHandlerContext ctx) {
		return (InetSocketAddress) ctx.channel().remoteAddress();
	}

}
