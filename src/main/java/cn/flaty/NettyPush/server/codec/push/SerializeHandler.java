package cn.flaty.NettyPush.server.codec.push;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.flaty.NettyPush.utils.FastJsonUtils;

public class SerializeHandler extends MessageToByteEncoder<Object> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out)
			throws Exception {
		String s = FastJsonUtils.toJsonString(msg);
		if (StringUtils.isNotEmpty(s)){
			out.writeBytes(s.getBytes());
		}
	}
}
