package cn.flaty.NettyPush.server.frame;

import java.nio.charset.Charset;

import cn.flaty.NettyPush.server.frame.FrameHead.charset;
import cn.flaty.NettyPush.utils.CharsetUtil;

/**
 * 组装包
 *
 * @author flatychen
 *
 */
public class SimplePushOutFrame {

	private FrameHead frameHead;

	private charset charset;

	private byte[] length;

	private byte[] head;

	private byte[] body;

	public SimplePushOutFrame(FrameHead frameHead, String body) {
		super();
		if (body == null || body.length() <= 0) {
			throw new IllegalArgumentException("body不可为空或长度非法");
		}
		this.frameHead = frameHead;
		this.init(body);
	}

	private void init(String body) {
		length = new byte[frameHead.headLength()];
		head = new byte[frameHead.headLength()];
		this.body = body.getBytes();


		this.charset = this.setCharset(body);
		this.setHead();
		this.setLength(this.body.length);

	}


	private void setHead() {
		head[0] = (byte) charset.ordinal();
		head[1] = (byte) FrameHead.encype.NULL.ordinal();

	}

	private charset setCharset(String body) {
		String charsetName = Charset.defaultCharset().name();
		charset charsetType = null;
		if (CharsetUtil.UTF_8.name().equalsIgnoreCase(charsetName)) {
			charsetType = FrameHead.charset.UTF_8;
		} else if (CharsetUtil.GBK.name().equalsIgnoreCase(charsetName)) {
			charsetType = FrameHead.charset.GBK;
		} else if (CharsetUtil.GB2312.displayName().equalsIgnoreCase(
				charsetName)) {
			charsetType = FrameHead.charset.GB2312;
		} else if (CharsetUtil.US_ASCII.displayName().equalsIgnoreCase(
				charsetName)) {
			charsetType = FrameHead.charset.US_ASCII;
		} else if (CharsetUtil.ISO_8859_1.displayName().equalsIgnoreCase(
				charsetName)) {
			charsetType = FrameHead.charset.ISO_8859_1;
		}
		return charsetType;
	}

	private void setLength(int l) {
		this.length = this.frameHead.intToBytes(l);
	}

	public byte[] getBody() {
		return body;
	}

	public byte[] getHead() {
		return head;
	}

	public byte[] getLength() {
		return length;
	}


}
