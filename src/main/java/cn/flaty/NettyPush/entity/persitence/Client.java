package cn.flaty.NettyPush.entity.persitence;

public class Client {

	private String did;

	private long expireTime;

	public long getUpdateTime() {
		return expireTime;
	}

	public void setUpdateTime(long expireTime) {
		this.expireTime = expireTime;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

}
