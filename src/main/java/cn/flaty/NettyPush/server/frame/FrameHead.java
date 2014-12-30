package cn.flaty.NettyPush.server.frame;

public interface  FrameHead {
	

	/**
	 * 首部长度所占字节
	 * @return
	 */
	int byteLength();

	/**
	 * 首部长度所占字节最大长度
	 * @return
	 */
	int maxLength();

	/**
	 * 长度转int
	 * @param bytes
	 * @return
	 */
	int bytesToLength(byte[] bytes);

	/** 
	 * int 转字节数组
	 * @param length
	 * @return
	 */
	byte[] lengthToBytes(int length);

	/**
	 * 包头所占字节
	 * @return
	 */
	int headLength();
	
	/**
	 * 包头
	 * @return
	 */
	byte[] head();
	
}
