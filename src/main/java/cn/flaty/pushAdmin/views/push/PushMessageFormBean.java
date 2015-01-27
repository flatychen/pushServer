package cn.flaty.pushAdmin.views.push;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.validator.constraints.NotEmpty;

import cn.flaty.NettyPush.entity.PushMessage;

public class PushMessageFormBean {

	@NotEmpty
	private String title;

	@NotEmpty
	private String content;

	@NotEmpty
	private byte[] flags;

	private int pushAction ;

	private String openUrl;

	private String openActivity;

	public byte[] getFlags() {
		return flags;
	}

	public void setFlags(byte[] flags) {
		this.flags = flags;
	}


	public int getPushAction() {
		return pushAction;
	}

	public void setPushAction(int pushAction) {
		this.pushAction = pushAction;
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

	public PushMessage parsePushMessage() {
		PushMessage pm = new PushMessage();
		
		for (int i = 0; i < this.flags.length; i++) {
			pm.setFlag(pm.getFlag() | this.flags[i]);
		}
		
		try {
			PropertyUtils.copyProperties(pm, this);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
		
		return pm;
	}

}
