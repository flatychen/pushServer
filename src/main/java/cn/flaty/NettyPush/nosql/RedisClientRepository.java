package cn.flaty.NettyPush.nosql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.flaty.NettyPush.entity.packet.ClientPacket;

/**
 * 
 * <pre>
 * did -> 设备ID
 * appkey -> app唯一标识码
 * appClientUId = md5(appKey+did)    
 * 
 * client:[id] -> [appClientUId]  **[String]**  **expire[x second]** 
 * [appkey] -> id  **[set]**
 * [appkey]:[os] -> id         **[set]** 
 * [appkey]:[appVer] -> id     **[set]**
 * [appkey]:[label] -> id         **[set]**
 * </pre>
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

//	@Autowired
	private JedisTemplate jedisTemplate;

	private static Logger logger = LoggerFactory
			.getLogger(RedisClientRepository.class);

	/**
	 * 
	 * 
	 * @param client
	 * @return
	 * @author flatychen
	 */
	public void saveClient(ClientPacket client) {
		jedisTemplate.hset(clientsKeyPrefix + client.getAppKey(),
				client.getRedisField(), client.getAppClientUID());
		jedisTemplate.setex(clientKeyPrefix + client.getAppClientUID(), "1",
				clientExpireTime);
	}

	/**
	 * 
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
