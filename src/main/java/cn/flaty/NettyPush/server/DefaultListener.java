package cn.flaty.NettyPush.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.MessageSizeEstimator.Handle;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import cn.flaty.NettyPush.utils.RuntimeUtils;

/**
 * 
 * 默认监听器
 * @author flaty
 *
 */
public class DefaultListener implements Listener{
	
	private  Logger log = LoggerFactory.getLogger(DefaultListener.class);
	
	private ChannelInitializer<Channel> channelInitializer;

	private int port;

	private String host;
	

	public void start() {
		// acceptor 线程，用于监听 相当于 bio accept,并分发至worker线程
		EventLoopGroup acceptor = new NioEventLoopGroup(1);
		// work线程，用于处理，取当前服务器 cpu 数量  * 2
		EventLoopGroup worker = new NioEventLoopGroup(RuntimeUtils.getProcessors() * 2);
		try {
			// 启动器
			ServerBootstrap sbs = new ServerBootstrap();
			sbs.group(acceptor, worker).channel(NioServerSocketChannel.class)
//					.childOption(ChannelOption.SO_KEEPALIVE, true)
//					.childOption(ChannelOption.TCP_NODELAY, true) 
//					.childOption(ChannelOption.SO_REUSEADDR, true) 
					.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT) // 使用内存池 回收堆外内存
//					.childOption(ChannelOption.MESSAGE_SIZE_ESTIMATOR, new MessageSizeEstimator() {
//						@Override
//						public Handle newHandle() {
//							return new Handle() {
//								@Override
//								public int size(Object msg) {
//									return 256;
//								}
//							};
//						}
//					}) // 使用内存池 回收堆外内存
					
					.localAddress(host, port)
					.handler(new LoggingHandler(LogLevel.INFO))
					.childHandler(channelInitializer);
			log.info(MessageFormat.format("server start and listen on port:{0}",String.valueOf(this.port)));
			ChannelFuture f = sbs.bind().sync();
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} finally{
			acceptor.shutdownGracefully();
			worker.shutdownGracefully();
		}
	}

	@Required
	public void setChannelInitializer(ChannelInitializer<Channel> channelInitializer) {
		this.channelInitializer = channelInitializer;
	}

	@Required
	public void setPort(int port) {
		this.port = port;
	}

	@Required
	public void setHost(String host) {
		this.host = host;
	}

	

}
