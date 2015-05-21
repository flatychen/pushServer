package cn.flaty.NettyPush.server.conn;

import java.util.Map;

/**
 * 
 * 本地TCP连接池
 * 
 * @author flatychen
 * 
 * @param <K>
 * @param <V>
 */
public interface NettyConnectionPool<K, V> {

	/**
	 * 放连接
	 * 
	 * @param key
	 * @param t
	 * @return
	 * @author flatychen
	 */
	public boolean set(K key, V t);

	/**
	 * 刷新连接
	 * 
	 * @param key
	 * @return
	 * @author flatychen
	 */
	public boolean touch(K key);

	/**
	 * 
	 * 根据key取连接
	 * 
	 * @param key
	 * @return
	 * @author flatychen
	 */
	public V get(K key);

	/**
	 * 消耗性能，仅用于测试！
	 * 
	 * @return
	 */
	@Deprecated
	public Map<K, V> asMap();

}
