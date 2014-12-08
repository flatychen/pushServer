package cn.flaty.NettyPush.services;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

import java.util.Set;

import org.apache.commons.lang.StringUtils;

public class DefaultMessageHandler implements ServiceHandler {

	private Set<ChannelHandlerContext> ctxs = ChannelHandlerContextMgr.ctxs;

	@Override
	public void handle(Object o) {
		String msg = null;
		if (o instanceof String) {
			msg = (String) o;
		}else{
			throw new IllegalArgumentException("-->参数不是String类型！");
		}
		
		if(StringUtils.isBlank(msg)){
			return ;
		}
		
		for (ChannelHandlerContext ctx : ctxs) {
			this.writeMsg(ctx,msg);
		}
	}
	
	
	
	public void writeMsg(ChannelHandlerContext ctx , String msg ){
		//for (int i = 0; i < 5; i++) {
			ctx.writeAndFlush(Unpooled.wrappedBuffer(msg.getBytes()));
		//}
	}
	
}
