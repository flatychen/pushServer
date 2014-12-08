package cn.flaty.NettyPush.server.inHandler;

import javax.annotation.Resource;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import cn.flaty.NettyPush.server.conn.ConnectionPool;
import cn.flaty.NettyPush.server.conn.NettyConnection;

@Sharable
public class MemcachedDemoHandler extends ChannelInboundHandlerAdapter {
	
	private ConnectionPool pool;

	public void setPool(ConnectionPool pool) {
		this.pool = pool;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		pool.set("demo", 0, new NettyConnection(ctx));
		this.send();
	}

	private void send() {
		try {
			Thread.sleep(50000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NettyConnection conn = pool.get("demo");
		String msg = "msg test";
		conn.writeAndFlush(Unpooled.wrappedBuffer(msg.getBytes()));
	}

}
