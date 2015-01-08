package cn.flaty.NettyPush.server.frame;

/**
 * 组装包
 * 
 * @author flatychen
 *
 */
public class SimplePushOutFrame {

	private FrameHead frameHead;

	//private byte[] length;

	private byte[] head;

	private byte[] body;

	public SimplePushOutFrame(FrameHead frameHead, byte[] body) {
		super();
		if (body == null || body.length <= 0) {
			throw new IllegalArgumentException("---->body不可为空或长度非法");
		}

		this.frameHead = frameHead;
		this.init(body);
	}

	private void init(byte[] body) {
	//	length = new byte[frameHead.headLength()];
		head = new byte[frameHead.headLength()];

		//this.setLength(body.length);

		this.setHead();

		this.setBody(body);
	}

	private void setBody(byte[] body) {
		this.body = body;

	}

	private void setHead() {
		head[0] = (byte) FrameHead.charset.UTF_8.ordinal();
		head[1] = (byte) FrameHead.encype.NULL.ordinal();
	}

	/*private void setLength(int l) {
		this.length = this.frameHead.intToBytes(l);
	}*/

	public byte[] getBody() {
		return body;
	}

	public byte[] getHead() {
		return head;
	}

	/*public byte[] getLength() {
		return length;
	}*/

}
