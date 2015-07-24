package cn.flaty.NettyPush.nosql;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;
import cn.flaty.NettyPush.nosql.JedisTemplate.JedisAction;

public class JedisUtils {

	private static final String OK_CODE = "OK";
	private static final String OK_MULTI_CODE = "+OK";

	/**
	 * 判断 返回值是否ok.
	 */
	public static boolean isStatusOk(String status) {
		return (status != null)
				&& (OK_CODE.equals(status) || OK_MULTI_CODE.equals(status));
	}

	/**
	 * Ping the jedis instance, return true is the result is PONG.
	 */
	public static boolean ping(JedisPool pool) {
		JedisTemplate template = new JedisTemplate(pool);
		try {
			String result = template.execute(new JedisAction<String>() {
				@Override
				public String action(Jedis jedis) {
					return jedis.ping();
				}
			});
			return (result != null) && result.equals("PONG");
		} catch (JedisException e) {
			return false;
		}
	}

}
