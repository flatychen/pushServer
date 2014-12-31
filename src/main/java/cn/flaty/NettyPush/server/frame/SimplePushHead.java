package cn.flaty.NettyPush.server.frame;

public  class SimplePushHead implements FrameHead {

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

	

}
