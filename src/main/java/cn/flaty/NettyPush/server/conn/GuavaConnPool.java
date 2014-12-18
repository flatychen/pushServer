package cn.flaty.NettyPush.server.conn;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class GuavaConnPool implements NettyConnectionPool<String, String> {

	private long accessTime = 30;

	private LoadingCache<String, String> cache = CacheBuilder.newBuilder()
			.expireAfterAccess(accessTime, TimeUnit.SECONDS).removalListener(new RemovalListener<String, String>() {
				@Override
				public void onRemoval(
						RemovalNotification<String, String> notification) {
					System.out.println(notification.getKey()+"is removing ");
					
				}
			})
			.build(new CacheLoader<String, String>() {
				@Override
				public String load(String key) throws Exception {
					System.out.println("cacheloader");
					return (String) "";
				}

			});

	@Override
	public boolean set(String key, String t) {
		cache.put(key, t);
		return false;
	}

	@Override
	public String get(String key) {
		try {
			return cache.get(key);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean touch(String key) {
		cache.refresh(key);
		return true;
	}



}
