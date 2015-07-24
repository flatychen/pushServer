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
public class MessageLog {

	private String appKey;

	private String title;

	private String content;

	private int flag;

	private String pushActionMixin;

	private long createTime;

	private long expireTime;

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
