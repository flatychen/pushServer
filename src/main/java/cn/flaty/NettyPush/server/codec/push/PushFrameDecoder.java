package cn.flaty.NettyPush.server.codec.push;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.net.InetSocketAddress;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.flaty.NettyPush.server.frame.FrameHead;
import cn.flaty.NettyPush.server.frame.SimplePushInFrame;
import cn.flaty.NettyPush.utils.AssertUtils;

/**
 * 
 * 根据包头解码
 * 
 * @author flatychen
 * 
 */
public class PushFrameDecoder extends ByteToMessageDecoder {

	private Logger log = LoggerFactory.getLogger(PushFrameDecoder.class);

	private FrameHead frameHead;

	public PushFrameDecoder(FrameHead frameHead) {
		super();
		AssertUtils.notNull(frameHead, " frameHead 不能为空 ");
		this.frameHead = frameHead;
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {

		int bytesHaveRead = in.readableBytes();

		// 读到0字节时且有读事件发生时为TCP rst重置
		if (bytesHaveRead == 0) {
			InetSocketAddress isa = (InetSocketAddress) ctx.channel()
					.remoteAddress();
			log.warn("----> {} 关闭 ", isa.toString());
			in.release();
			return;
		}

		byte[] bytes = new byte[bytesHaveRead];
		in.readBytes(bytes);
		// 包解码
		SimplePushInFrame frame = new SimplePushInFrame(frameHead, bytes);
		String _s = frame.getBody();
		out.add(_s);
	}

}
