package cn.flaty.NettyPush.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpHeaders.Names;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.HttpHeaders.Values;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;

import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpDemoHandler extends ChannelInboundHandlerAdapter {

	private Logger log = LoggerFactory.getLogger(HttpDemoHandler.class);

	/*@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		InetSocketAddress socket = this.getClientSocket(ctx);
		log.info(MessageFormat.format("one client connected; host:{1}", 1,
				InetSocketUtils.getSocketAddress(socket)));
	}*/

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		System.out.println(msg);
		this.writeMsg(ctx, msg);
	}

	
	public final InetSocketAddress getClientSocket(ChannelHandlerContext ctx) {
		return (InetSocketAddress) ctx.channel().remoteAddress();
	}
	
	
	
	private void writeMsg(ChannelHandlerContext ctx, Object msg){
		//ctx.writeAndFlush(Unpooled.wrappedBuffer("test".getBytes()));
		if (msg instanceof HttpRequest) {  
			
            HttpRequest request = (HttpRequest) msg;  
            System.out.println("messageType:" + request.headers().get("messageType"));  
//        }  
//        if (msg instanceof HttpContent) {  
            FullHttpResponse response = new DefaultFullHttpResponse( HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer("I am ok"  
                    .getBytes()));  
            response.headers().set(Names.CONTENT_TYPE, "text/plain");  
            response.headers().set(Names.CONTENT_LENGTH, response.content().readableBytes());  
            response.headers().set(Names.CONNECTION, Values.KEEP_ALIVE);  
            ctx.writeAndFlush(response);  
            try {
				Thread.sleep(5000);
				  FullHttpResponse response2 = new DefaultFullHttpResponse( HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer("I am okf dfd"  
		                    .getBytes()));  
				  response2.headers().set(Names.CONTENT_TYPE, "text/plain");  
				  response2.headers().set(Names.CONTENT_LENGTH, response.content().readableBytes());  
				  response2.headers().set(Names.CONNECTION, Values.KEEP_ALIVE);  
				  System.out.println("2");
				  ctx.writeAndFlush(response2);  
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }  
	}
	
}




