package cn.flaty.NettyPush.server.frame;

/**
 * 帧首部长度字节接口，用于处理tcp粘帧，拆帧
 * 
 * @author flatychen
 *
 */
public interface FrameLength {

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
}
