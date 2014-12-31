package cn.flaty.NettyPush.server.channelInitializer;

import io.netty.channel.ChannelPipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.flaty.NettyPush.server.codec.push.DeserializeHandler;
import cn.flaty.NettyPush.server.codec.push.PushFrameDecoder;
import cn.flaty.NettyPush.server.codec.push.SplitFrameDecoder;

public class SocketChannelInitializer extends  AbstractChannelInitializer{

	
	private static Logger log = LoggerFactory.getLogger(SocketChannelInitializer.class);
	
	@Override
	protected void initPipeline(ChannelPipeline pipeline) {
		
		// 解码器
		
		
		
		// 编码器
		//pipeline.addLast(new PushFrameDecoder());
		//pipeline.addLast(new PushFrameDecoder());
		
	}


}
