package cn.flaty.NettyPush.server.channelInitializer;

import io.netty.channel.ChannelPipeline;
import cn.flaty.NettyPush.server.codec.sample.EchoHandler;
import cn.flaty.NettyPush.server.codec.sample.ShowMsgHandler;

/**
 *
 * @author flatychen
 *
 */
public class SampleChannelInitializer extends  AbstractChannelInitializer{
	

	@Override
	protected void initPipeline(ChannelPipeline pipeline) {
		 pipeline.addFirst( new ShowMsgHandler());
	}

}
