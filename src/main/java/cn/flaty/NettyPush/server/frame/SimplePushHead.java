package cn.flaty.NettyPush.server.frame;

import cn.flaty.NettyPush.utils.ByteUtil;


public abstract class SimplePushHead implements FrameHead {
	

	public final int FRAME_LENGTH_BYTES = 4;
	
	public final int MAX_LENGTH = Integer.MAX_VALUE;
	
	public final int HEAD_LENGTH_BYTES = 4;

	
	private static volatile SimplePushHead head;
	
	
	private SimplePushHead() {
	}


	public static FrameHead getInstance(){
		if( head == null ){
			synchronized (head) {
				return new SimplePushHead() {
				};
			}
		}
		return head;
	}
	
	
	@Override
	public int byteLength() {
		return FRAME_LENGTH_BYTES;
	}

	@Override
	public int maxLength() {
		return MAX_LENGTH;
	}

	@Override
	public int bytesToLength(byte[] bytes) {
		return ByteUtil.byteArrayToInt(bytes);
	}

	@Override
	public byte[] lengthToBytes(int length) {
		return ByteUtil.intToByteArray(length);
	}

	@Override
	public int headLength() {
		return HEAD_LENGTH_BYTES;
	}

	@Override
	public byte[] head(){
		return null;
	}

}
