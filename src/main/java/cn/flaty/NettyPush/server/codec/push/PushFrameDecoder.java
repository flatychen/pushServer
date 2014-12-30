package cn.flaty.NettyPush.server.codec.push;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.flaty.NettyPush.server.frame.FrameHead;

public class PushFrameDecoder extends ByteToMessageDecoder {
	
	
	
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		
		
		
		// 解析报文头
		try {
			this.praseHeader(in);
		} catch (Exception e) {
			e.printStackTrace();
			return ;
		}
		// 解析报文
		String _s = null;
		try {
			_s = this.praseBody(in);
		} catch (Exception e) {
			e.printStackTrace();
			return ;
		}
		if(StringUtils.isNotBlank(_s)){
			out.add(_s);
		}
	}

	/**
	 * 解析报文体
	 * @param in
	 * @return
	 */
	private String praseBody(ByteBuf in) {
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
