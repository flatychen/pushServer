package cn.flaty.NettyPush.server.channelInitializer;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

/**
 * 
 * 抽像ChannelInitializer <br>
 * 
 * 用于组织不同的channel
 * 
 * @author flatychen
 * 
 */
public abstract class AbstractChannelInitializer extends ChannelInitializer {

	private ChannelHandler[] inHandlers;

	private ChannelHandler[] outHandlers;

	public void setInHandlers(ChannelHandler[] inHandlers) {
		this.inHandlers = inHandlers;
	}

	public void setOutHandlers(ChannelHandler[] outHandlers) {
		this.outHandlers = outHandlers;
	}

	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		if (inHandlers != null) {
			pipeline.addFirst(inHandlers);
		}
		this.initPipeline(pipeline);
		if (outHandlers != null) {
			// pipeline.addl(outHandlers);
		}

	}

	protected abstract void initPipeline(ChannelPipeline pipeline);

}
