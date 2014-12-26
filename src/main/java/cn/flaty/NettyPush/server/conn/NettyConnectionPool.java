package cn.flaty.NettyPush.server.conn;

import java.util.Map;

public interface NettyConnectionPool<K, V> {

	public boolean set(K key, V t);

	public boolean touch(K key);

	public V get(K key);

	/**
	 * 消耗性能，仅用于测试！
	 * @return
	 */
	public Map<K, V> asMap();

}
