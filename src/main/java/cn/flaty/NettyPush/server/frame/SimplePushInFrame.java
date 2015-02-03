package cn.flaty.NettyPush.server.frame;

import cn.flaty.NettyPush.utils.AssertUtils;
import cn.flaty.NettyPush.utils.CharsetUtil;

/**
 *
 * 解码包
 * @author flatychen
 *
 */
public class SimplePushInFrame {
	private FrameHead frameHead;

	private byte[] head;

	private String body;


	public SimplePushInFrame(FrameHead frameHead, byte[] frame) {
		super();
		this.frameHead = frameHead;
		this.init(frame);
		this.frameHead = frameHead;
	}


	private void init(byte[] frame) {
		if( frame.length <= frameHead.byteLength()){
			throw new IllegalArgumentException(" frame 内容不能为空 ");
		}

		head = new byte[frameHead.headLength()];

		System.arraycopy(frame, 0, head, 0, frameHead.headLength());

		this.setBody(frame,frame.length - frameHead.headLength());
	}



	private void setBody(byte[] body,int bodyLength) {

		byte _body [] = body;

		this.setCharset(_body,bodyLength);

	}


	private void setCharset(byte[] _body,int bodyLength) {
		int charsetType = head[0];
		String s = null;
		FrameHead.charset c = FrameHead.charset.values()[charsetType];
		switch (c) {
		case NULL:
			s = new String(_body);
			break;
		case UTF_8:
			s = new String(_body, frameHead.headLength(), bodyLength, CharsetUtil.UTF_8);
			break;
		case US_ASCII:
			s = new String(_body, frameHead.headLength(), bodyLength, CharsetUtil.US_ASCII);
			break;
		case ISO_8859_1:
			s = new String(_body, frameHead.headLength(), bodyLength, CharsetUtil.ISO_8859_1);
			break;
		case GBK:
			s = new String(_body, frameHead.headLength(), bodyLength, CharsetUtil.GBK);
			break;
		case GB2312:
			s = new String(_body, frameHead.headLength(), bodyLength,CharsetUtil.GB2312);
			break;
		}
		this.body = s;

	}


	public byte getEncypeType(){
		AssertUtils.notNull(head, " 包头不能为空");
		return head[0];
	}

	public byte getCharsetType(){
		AssertUtils.notNull(head, " 包头不能为空");
		return head[1];
	}


	public String getBody() {
		return body;
	}




}
