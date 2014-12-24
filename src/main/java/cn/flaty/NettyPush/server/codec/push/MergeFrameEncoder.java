package cn.flaty.NettyPush.server.codec.push;

import cn.flaty.NettyPush.server.frame.PushFrame;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.MessageToByteEncoder;


/**
 * @author flaty
 *
 */
public class MergeFrameEncoder extends LengthFieldPrepender {

	public MergeFrameEncoder(){
		super(PushFrame.head_length_bytes,PushFrame.head_bytes - PushFrame.head_length_bytes, false);
	}

}
