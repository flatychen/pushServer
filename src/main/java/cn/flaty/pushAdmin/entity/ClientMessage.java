package cn.flaty.pushAdmin.entity;

public class ClientMessage {
	private String did;
	private Long msgId;
	private Long sendTime;

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}


	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public Long getSendTime() {
		return sendTime;
	}

	public void setSendTime(Long sendTime) {
		this.sendTime = sendTime;
	}

}
