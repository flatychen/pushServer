package cn.flaty.NettyPush.utils;

public class PushFrameHeadler {
	
	/**
	 *  数据编码格式。已定义：0：UTF-8，1：GBK，2：GB2312，3：ISO8859-1
	 */
	private byte encode;
	/**
	 * 加密类型。0表示不加密
	 */
	private byte encrypt;
	/**
	 * 用于扩展协议。暂未定义任何值
	 */
	private byte extend1;
	/**
	 * 用于扩展协议。暂未定义任何值
	 */
	private byte extend2;
	
	/** 数据包长 **/
	private int length;

	public byte getEncode() {
		return encode;
	}

	public void setEncode(byte encode) {
		this.encode = encode;
	}

	public byte getEncrypt() {
		return encrypt;
	}

	public void setEncrypt(byte encrypt) {
		this.encrypt = encrypt;
	}

	public byte getExtend1() {
		return extend1;
	}

	public void setExtend1(byte extend1) {
		this.extend1 = extend1;
	}

	public byte getExtend2() {
		return extend2;
	}

	public void setExtend2(byte extend2) {
		this.extend2 = extend2;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	

}
