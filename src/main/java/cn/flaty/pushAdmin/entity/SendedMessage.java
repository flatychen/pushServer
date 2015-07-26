package cn.flaty.pushAdmin.entity;

/**
 * 
 * 幸存消息,也即未过期消息
 * 
 * @author flatychen
 * 
 */
public class SendedMessage {

	private String appKey;

	private String did;

	private long msgId;

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public long getMsgId() {
		return msgId;
	}

	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}
}
