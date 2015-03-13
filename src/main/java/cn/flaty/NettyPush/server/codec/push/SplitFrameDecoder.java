package cn.flaty.NettyPush.server.codec.push;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import cn.flaty.NettyPush.server.frame.FrameHead;

/**
 * 
 * 根据长度切包
 * 
 * @author flatychen
 *
 */
public class SplitFrameDecoder extends LengthFieldBasedFrameDecoder {

	public SplitFrameDecoder(FrameHead frameHead) {
		super(frameHead.maxLength(), 0, frameHead.byteLength(), frameHead
				.headLength(), frameHead.byteLength());
	}

	/* 
	 * 防止内存拷贝
	 */
	@Override
	protected ByteBuf extractFrame(ChannelHandlerContext ctx, ByteBuf buffer,
			int index, int length) {
		// 增加一次引用计数
		buffer.retain();
		return buffer.slice(index, length);
	}
}
