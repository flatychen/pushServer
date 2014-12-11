package cn.flaty.NettyPush.server.conn;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class GuavaConnPool implements NettyConnectionPool {

	private long accessTime;

	private LoadingCache<String, String> cahceBuilder = CacheBuilder.newBuilder()
			.expireAfterAccess(accessTime, TimeUnit.SECONDS).removalListener(new RemovalListener<String, String>() {
				@Override
				public void onRemoval(
						RemovalNotification<String, String> notification) {
					
				}
			})
			.build(new CacheLoader<String, String>() {
				@Override
				public String load(String key) throws Exception {
					String strProValue = "hello " + key + "!";
					return strProValue;
				}

			});

	@Override
	public <T> boolean set(String key, int exp, T t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T get(String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
