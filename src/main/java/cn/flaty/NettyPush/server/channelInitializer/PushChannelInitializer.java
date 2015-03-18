package cn.flaty.NettyPush.server.channelInitializer;

import io.netty.channel.ChannelPipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import cn.flaty.NettyPush.server.codec.push.DeserializeHandler;
import cn.flaty.NettyPush.server.codec.push.MergeFrameEncoder;
import cn.flaty.NettyPush.server.codec.push.PushFrameDecoder;
import cn.flaty.NettyPush.server.codec.push.PushFrameEncoder;
import cn.flaty.NettyPush.server.codec.push.SplitFrameDecoder;
import cn.flaty.NettyPush.server.protocol.FrameHead;

/**
 * 
 * 消息推送channel注册
 * @author flatychen
 *
 */
public class PushChannelInitializer extends  AbstractChannelInitializer{

	
	private static Logger log = LoggerFactory.getLogger(PushChannelInitializer.class);
	
	private FrameHead frameHeader;
	
	@Override
	protected void initPipeline(ChannelPipeline pipeline) {
		// 解码器
		pipeline.addLast(new SplitFrameDecoder(frameHeader));
		pipeline.addLast(new PushFrameDecoder(frameHeader));
		
		// 编码器
		pipeline.addLast(new MergeFrameEncoder(frameHeader));
		
		pipeline.addLast(new PushFrameEncoder(frameHeader));
		
		pipeline.addLast(new DeserializeHandler());
		
		
	}

	@Required
	public void setFrameHeader(FrameHead frameHeader) {
		this.frameHeader = frameHeader;
	}


}
