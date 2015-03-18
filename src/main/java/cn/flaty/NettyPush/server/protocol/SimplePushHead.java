package cn.flaty.NettyPush.server.protocol;

import cn.flaty.NettyPush.utils.ByteUtil;

/**
 * 简单TCP包 <br><br><br>
 * <b>包头共8个字节</b> <br>
 * |-4字节-|1字节|1字节|1字节|<br>
 * |  长度   |编码  | 保留  |保留  |
 * 
 * @author flatychen
 *
 */
public class SimplePushHead implements FrameHead {

	public final int FRAME_LENGTH_BYTES = 4;

	public final int MAX_LENGTH = Integer.MAX_VALUE;

	public final int HEAD_LENGTH_BYTES = 4;

	@Override
	public int byteLength() {
		return FRAME_LENGTH_BYTES;
	}

	@Override
	public int maxLength() {
		return MAX_LENGTH;
	}

	@Override
	public int headLength() {
		return HEAD_LENGTH_BYTES;
	}

	@Override
	public int bytesToInt(byte[] b) {
		if (b.length != this.FRAME_LENGTH_BYTES) {
			throw new IllegalArgumentException("----> 包长度数组非法");
		}
		return ByteUtil.byteArrayToInt(b);
	}

	@Override
	public byte[] intToBytes(int length) {
		return ByteUtil.intToByteArray(length);
	}

}
