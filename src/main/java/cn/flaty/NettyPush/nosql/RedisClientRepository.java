package cn.flaty.NettyPush.nosql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.flaty.NettyPush.entity.packet.ClientPacket;

/**
 * 
 * 
 * @author flatychen
 * 
 */
@Repository
public class RedisClientRepository {

	private static int clientExpireTime = 30;
	private static String clientsKeyPrefix = "clients:";
	private static String clientKeyPrefix = "client:";

	@Autowired
	private JedisTemplate jedisTemplate;

	private static Logger logger = LoggerFactory
			.getLogger(RedisClientRepository.class);

	/**
	 * 
	 * 客户端列表 clients:[appkey] -> [did]:[os]:[version]:[label] -> [AppClientUID] <br>
	 * 客户端自动刷新 client:[AppClientUID] -> 1
	 * 
	 * @param client
	 * @return
	 * @author flatychen
	 */
	public void setClientKey(ClientPacket client) {
		jedisTemplate.hset(clientsKeyPrefix + client.getAppKey(),
				client.getRedisField(), client.getAppClientUID());
		jedisTemplate.setex(clientKeyPrefix + client.getAppClientUID(), "1",
				clientExpireTime);
	}

	/**
	 * 
	 * 客户端自动刷新 client:[AppClientUID] -> 1
	 * 
	 * @param client
	 * @return
	 * @author flatychen
	 */
	public void touchClient(ClientPacket client) {
		jedisTemplate.setex(clientKeyPrefix + client.getAppClientUID(), "1",
				clientExpireTime);
	}

}
