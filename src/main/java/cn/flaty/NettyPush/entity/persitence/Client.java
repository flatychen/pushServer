package cn.flaty.NettyPush.entity.persitence;

import cn.flaty.NettyPush.entity.packet.ClientPacket;

public class Client extends ClientPacket {

	private long expireTime;

	public long getUpdateTime() {
		return expireTime;
	}

	public void setUpdateTime(long expireTime) {
		this.expireTime = expireTime;
	}

}
