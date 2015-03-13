package cn.flaty.NettyPush.model;

/**
 * 
 * 通用报文bean,commond为报文类型，message为报文内容
 * @author flatychen
 *
 */
public class GenericMessage {
	
	private int commond;
	
	private String message;

	public int getCommond() {
		return commond;
	}

	public void setCommond(int commond) {
		this.commond = commond;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
