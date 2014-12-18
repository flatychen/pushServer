package cn.flaty.NettyPush.server.codec.push;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.CharsetUtil;
import cn.flaty.NettyPush.server.frame.PushFrame;

public class PushFrameDecoder extends ByteToMessageDecoder {
	
	
	/**
	 * 报文头长度
	 */
	private static int _headLength = PushFrame.head_bytes - PushFrame.head_length_bytes;
	
	/**
	 * 报文头，四个字节
	 */
	private byte head[] = new byte[_headLength];

	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		this.praseHeader(in);
		Object o = this.praseBody(in);
		out.add(o);
	}

	/**
	 * 解析报文体
	 * @param in
	 * @return
	 */
	private Object praseBody(ByteBuf in) {
		int _bytes = in.readableBytes();
		byte[] b = new byte[_bytes];
		in.readBytes(b);
		return setCharset(this.decryptBody(b));
	}

	
	/**
	 * 解密
	 * @param b
	 * @return
	 */
	private byte[] decryptBody(byte b[]){
		byte charset =  head[0];
		byte[] _b= null;
		switch (charset) {
		case 1:
			break;
		case 0:
			_b = b;
			break;
		}
		return _b;
		
	}
	
	/**
	 * 设置编码
	 * @param b
	 * @return
	 */
	private String setCharset(byte b[]){
		byte charset =  head[1];
		String result = null;
		switch (charset) {
		case 0:
			result = new  String(b,CharsetUtil.US_ASCII);
			break;
		case 1:
			result = new  String(b,CharsetUtil.UTF_8);
			break;
		case 2:
			result = new  String(b,Charset.forName("GB2312"));
			break;
		}
		return result;
		
	}
	
	
	/**
	 * 读取报文头
	 * @param in
	 */
	private void praseHeader(ByteBuf in){
		in.readBytes(head);
	}
	
	
	
	

}
