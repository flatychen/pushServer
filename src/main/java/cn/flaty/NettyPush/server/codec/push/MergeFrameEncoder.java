package cn.flaty.NettyPush.server.codec.push;

import io.netty.handler.codec.LengthFieldPrepender;
import cn.flaty.NettyPush.server.frame.FrameHead;


/**
 * @author flaty
 *
 */
public class MergeFrameEncoder extends LengthFieldPrepender {

	public MergeFrameEncoder(FrameHead frameHead){
		super(frameHead.byteLength(),- frameHead.headLength(),false);
	}

}
