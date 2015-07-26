package cn.flaty.pushAdmin.entity;

/**
 * 
 * 幸存消息,也即未过期消息
 * 
 * @author flatychen
 * 
 *         <code>   消息日志表
	create table  tb_msg_log(
	appkey VARCHAR(100),
	msgId BIGINT,
	title VARCHAR(60),
	content VARCHAR(900),
	flag int,
	pushActionMixin VARCHAR(200),
	createTime  BIGINT,
	expireTime BIGINT
	);
	</code>
 * 
 */
public class Message extends PushMessagePacket {

	private String appKey;

	private long msgId;

	private long createTime;

	private long expireTime;

	public long getMsgId() {
		return msgId;
	}

	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}


	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}

}
