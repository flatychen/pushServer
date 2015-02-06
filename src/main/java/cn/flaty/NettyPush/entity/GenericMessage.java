package cn.flaty.NettyPush.entity;

public class GenericMessage {

	public static int server_push_text = 1024;

	public static int server_push_image = 1025;

	public static int client_heart = 4096;

	public static int client_connected = 4097;

	private int commond;

	private String message;

	public int getCommond() {
		return commond;
	}

	public GenericMessage(String message) {
		super();
		this.commond = Integer.parseInt(new String(message.substring(0, 4)));
		this.message = message.substring(4, message.length());
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
