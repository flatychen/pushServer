package cn.flaty.NettyPush.server.frame;

public interface  FrameHead {
	

	/**
	 * 用于切包的，帧长度所占字节数
	 * @return
	 */
	int byteLength();

	/**
	 * 帧最大支持的报文长度
	 * @return
	 */
	int maxLength();

	

	/**
	 * 帧头部所占字节
	 * @return
	 */
	int headLength();
	
	
}
