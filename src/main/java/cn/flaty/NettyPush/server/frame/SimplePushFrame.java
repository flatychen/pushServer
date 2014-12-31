package cn.flaty.NettyPush.server.frame;

import cn.flaty.NettyPush.utils.AssertUtils;



public  class SimplePushFrame  {
	
	private FrameHead frameHead;
	

	private byte[] head; 
	
	private byte[] body;


	public SimplePushFrame(FrameHead frameHead, byte[] frame) {
		super();
		AssertUtils.notNull(frameHead, "----> frameHead不可为空");
		AssertUtils.notNull(frameHead, "----> frame不可为空");
		this.frameHead = frameHead;
		this.init(frame);
	}


	private void init(byte[] frame) {
		if( frame.length <= frameHead.byteLength()){
			throw new IllegalArgumentException("----> frame 内容不能为空 ");
		}
		
		head = new byte[frameHead.headLength()];
		body = new byte[frame.length - frameHead.headLength() ];
		
		System.arraycopy(frame, 0, head, 0, frameHead.headLength());
		System.arraycopy(frame, frameHead.headLength(), body, 0, frame.length - frameHead.headLength());
	}

	
	public byte getEncypeType(){
		return head[0];
	}

	public byte getCharsetType(){
		return head[1];
	}
	


	public byte[] getBody() {
		return body;
	}
	
	
	
	
}
