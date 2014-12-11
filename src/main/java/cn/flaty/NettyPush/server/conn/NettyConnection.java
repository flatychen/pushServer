package cn.flaty.NettyPush.server.conn;

import java.io.Serializable;

import cn.flaty.NettyPush.utils.AssertUtils;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;

public class NettyConnection implements Serializable {
	
	private ChannelHandlerContext context;

	public NettyConnection(ChannelHandlerContext context) {
		super();
		AssertUtils.notNull(context, "----> context 不能为空");
		this.context = context;
	}
	
	
	
	public  ChannelFuture writeAndFlush(Object msg){
		 return context.writeAndFlush(msg);
	 }
	

}
