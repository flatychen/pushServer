package cn.flaty.NettyPush.entity;

public class GenericMessage {

	public static int server_push_text = 1024;

	public static int client_heart = 4096;

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
