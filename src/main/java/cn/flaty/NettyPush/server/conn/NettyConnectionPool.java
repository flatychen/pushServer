package cn.flaty.NettyPush.server.conn;

public interface NettyConnectionPool<K, V> {
	
	 public  boolean set(K key,V t);
	 
	 public  boolean touch(K key);
	
	 public   V get(K key);
	

}
