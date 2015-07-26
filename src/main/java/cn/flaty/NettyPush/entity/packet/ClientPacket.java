package cn.flaty.NettyPush.entity.packet;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 客户端信息
 * 
 * @author flatychen
 * 
 */
public class ClientPacket {

	@Override
	public String toString() {
		return "ClientPacket [appKey=" + appKey + ", did=" + did + ", appVer="
				+ appVer + ", os=" + os + "]";
	}

	private String appKey;

	private String did;

	private int appVer;

	private String os;

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

	public int getAppVer() {
		return appVer;
	}

	public void setAppVer(int appVer) {
		this.appVer = appVer;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	/**
	 * 得到APP连接唯一码
	 * 
	 * @return
	 * @author flatychen
	 */
	public String getAppClientUID() {
		return DigestUtils.md5(this.appKey + ":" + this.did).toString();
	}

	/**
	 * 组装成redis hash field
	 * 
	 * @return
	 * @author flatychen
	 */
	public String getRedisField() {
		return new StringBuilder().append(this.did).append(":").append(this.os)
				.append(":").append(this.appVer).toString();
	}

}
