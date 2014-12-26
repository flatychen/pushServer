package cn.flaty.NettyPush.server.conn;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import cn.flaty.NettyPush.utils.AssertUtils;

public class NettyConnection {
	
	
	private ChannelHandlerContext context;

	public NettyConnection(ChannelHandlerContext context) {
		super();
		AssertUtils.notNull(context, "----> context 不能为空");
		this.context = context;
	}
	
	
	
	public  ChannelFuture writeAndFlush(String msg){
		 return context.writeAndFlush(Unpooled.wrappedBuffer(msg.getBytes()));
	}
	

}
