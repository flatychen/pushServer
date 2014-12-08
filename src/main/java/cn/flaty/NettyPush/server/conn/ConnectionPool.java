package cn.flaty.NettyPush.server.conn;

public interface ConnectionPool {
	
	 public <T> boolean set(String key,int exp,T t);
	
	 public  <T> T get(String key);
	

}
