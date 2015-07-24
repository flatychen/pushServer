package cn.flaty.pushAdmin.views.push;

import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.validator.constraints.NotEmpty;

import cn.flaty.pushAdmin.entity.PushTextPacket;

/**
 * 
 * 推送消处表单
 * 
 * @author flatychen
 * 
 */
public class PushMessageForm {

	/**
	 * 标题
	 */
	@NotEmpty
	private String title;

	/**
	 * 内容
	 */
	@NotEmpty
	private String content;

	@NotEmpty
	private byte[] flags;

	private int pushAction;

	private String openUrl;

	private String openActivity;

	private Date expireDate;

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

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

	public PushTextPacket parsePushMessage() {
		PushTextPacket pm = new PushTextPacket();

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
