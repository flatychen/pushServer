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
import java.util.Random;
import java.util.concurrent.Executors;

@Sharable
public class EchoMsgHandler extends ChannelInboundHandlerAdapter {

	public final int echoTimes = 20;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ByteBuf in = (ByteBuf) msg;
		String s = in.toString(CharsetUtil.UTF_8);
		System.out.println(MessageFormat.format("received from [{1}]; msg:{3}",
				1, getRemoteHost(ctx), 3, s));
		in.release();
		String s2 =  EchoMsgHandler.getRandomString(4096);
		System.out.println(s2);
		this.writeMsg(ctx, s2);

	}

	private String getRemoteHost(ChannelHandlerContext ctx) {
		InetSocketAddress socket = (InetSocketAddress) ctx.channel()
				.remoteAddress();
		return socket.getHostName() + ":" + socket.getPort();
	}

	public static String getRandomString(int length) { //length表示生成字符串的长度  
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	 }     
	
	public void writeMsg(final ChannelHandlerContext ctx, final String msg) {
		try {
			System.out.println("sleep");
			Thread.sleep(20000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		Executors.newFixedThreadPool(echoTimes).submit(new Runnable() {
			@Override
			public void run() {
				ctx.writeAndFlush(Unpooled.wrappedBuffer(msg.getBytes()));

			}
		});

	}
}
