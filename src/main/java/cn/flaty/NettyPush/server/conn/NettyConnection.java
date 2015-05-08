package cn.flaty.NettyPush.server.conn;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import cn.flaty.NettyPush.utils.AssertUtils;
import cn.flaty.NettyPush.utils.FastJsonUtils;

public class NettyConnection {

	private ChannelHandlerContext context;

	public NettyConnection(ChannelHandlerContext context) {
		super();
		AssertUtils.notNull(context, "----> context 不能为空");
		this.context = context;
	}

	public ChannelFuture writeAndFlush(Object o) {
		String r = FastJsonUtils.toJsonString(o);
		return context.writeAndFlush(r);
	}

}
