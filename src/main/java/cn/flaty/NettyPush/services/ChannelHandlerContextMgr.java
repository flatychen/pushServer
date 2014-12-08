package cn.flaty.NettyPush.services;

import io.netty.channel.ChannelHandlerContext;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class ChannelHandlerContextMgr {
	
	
	public static Set<ChannelHandlerContext> ctxs = new CopyOnWriteArraySet<ChannelHandlerContext>();

	public static void put(ChannelHandlerContext ctx){
		ctxs.add(ctx);
	}
	
	
	
}
