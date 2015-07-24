package cn.flaty.NettyPush.server.channelInitializer;

import io.netty.channel.ChannelPipeline;

import org.springframework.beans.factory.annotation.Required;

import cn.flaty.NettyPush.server.codec.push.DeserializeHandler;
import cn.flaty.NettyPush.server.codec.push.MergeFrameEncoder;
import cn.flaty.NettyPush.server.codec.push.PushFrameDecoder;
import cn.flaty.NettyPush.server.codec.push.PushFrameEncoder;
import cn.flaty.NettyPush.server.codec.push.SplitFrameDecoder;
import cn.flaty.NettyPush.server.frame.FrameHead;

/**
 * 
 * 推送channel pipeline
 * 
 * @author flatychen
 * 
 */
public class PushChannelInitializer extends AbstractChannelInitializer {

	private FrameHead frameHeader;

	@Override
	protected void initPipeline(ChannelPipeline pipeline) {

		// 切包,粘包
		pipeline.addLast(new SplitFrameDecoder(frameHeader));
		// 解码
		pipeline.addLast(new PushFrameDecoder(frameHeader));

		// 编码器
		// 粘包
		pipeline.addLast(new MergeFrameEncoder(frameHeader));
		// 编码
		pipeline.addLast(new PushFrameEncoder(frameHeader));

		// 业务处理
		pipeline.addLast(new DeserializeHandler());

	}

	@Required
	public void setFrameHeader(FrameHead frameHeader) {
		this.frameHeader = frameHeader;
	}

}
