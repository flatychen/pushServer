package cn.flaty.NettyPush.entity;



public class PushMessage {
	
	private String title;
	
	private String content;

	private int flag;
	
	private String pushActionMixin;

	public String getPushActionMixin() {
		return pushActionMixin;
	}

	public void setPushActionMixin(String pushActionMixin) {
		this.pushActionMixin = pushActionMixin;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	
	

}
