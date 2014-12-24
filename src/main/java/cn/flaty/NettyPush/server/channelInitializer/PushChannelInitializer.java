package cn.flaty.NettyPush.server.channelInitializer;

import io.netty.channel.ChannelPipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.flaty.NettyPush.server.codec.push.DeserializeHandler;
import cn.flaty.NettyPush.server.codec.push.MergeFrameEncoder;
import cn.flaty.NettyPush.server.codec.push.PushFrameDecoder;
import cn.flaty.NettyPush.server.codec.push.PushFrameEncoder;
import cn.flaty.NettyPush.server.codec.push.SerializeHandler;
import cn.flaty.NettyPush.server.codec.push.SplitFrameDecoder;

public class PushChannelInitializer extends  AbstractChannelInitializer{

	
	private static Logger log = LoggerFactory.getLogger(PushChannelInitializer.class);
	
	@Override
	protected void initPipeline(ChannelPipeline pipeline) {
		
		// 解码器
		pipeline.addLast(new SplitFrameDecoder());
		pipeline.addLast(new PushFrameDecoder());
		pipeline.addLast(new DeserializeHandler());
		
		
		
		
		// 编码器
//		pipeline.addLast(new SerializeHandler());
//		pipeline.addLast(new PushFrameEncoder());
//		pipeline.addLast(new MergeFrameEncoder());
		
	}


}
