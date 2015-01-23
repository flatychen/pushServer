package cn.flaty.pushAdmin.views.push;

import org.hibernate.validator.constraints.NotEmpty;


public class PushMessage {
	
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private String content;

	@NotEmpty
	private byte[] flags;
	
	
	private byte pushAction = 1;
	
	
	private String openUrl;
	
	private String openActivity;
	
	
	
	
	public byte[] getFlags() {
		return flags;
	}

	public void setFlags(byte[] flags) {
		this.flags = flags;
	}

	public byte getPushAction() {
		return pushAction;
	}

	public void setPushAction(byte pushAction) {
		this.pushAction = pushAction;
	}

	public String getOpenUrl() {
		return openUrl;
	}

	public void setOpenUrl(String openUrl) {
		this.openUrl = openUrl;
	}

	public String getOpenActivity() {
		return openActivity;
	}

	public void setOpenActivity(String openActivity) {
		this.openActivity = openActivity;
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

	
	

}
