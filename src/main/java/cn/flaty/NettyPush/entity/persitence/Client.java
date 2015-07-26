package cn.flaty.NettyPush.entity.persitence;

import cn.flaty.NettyPush.entity.packet.ClientPacket;

public class Client extends ClientPacket {

	private long expireTime;

	public long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}


}
