package conn;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import cn.flaty.NettyPush.server.conn.GuavaConnPool;
import cn.flaty.NettyPush.server.conn.NettyConnection;
import cn.flaty.NettyPush.server.conn.NettyConnectionPool;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class GuavaConnPoolTest {

	private LoadingCache<String, String> pool;

	@Before
	public void init() {
		pool = CacheBuilder
				.newBuilder()
				.expireAfterAccess(30, TimeUnit.SECONDS)
				.removalListener(
						new RemovalListener<String, String>() {
							@Override
							public void onRemoval(
									RemovalNotification<String, String> notification) {
							}
						}).build(new CacheLoader<String, String>() {
					@Override
					public String load(String key) throws Exception {
						System.out.println("load");
						return null;
					}

				});

	}

	@Test
	public void test() {
		this.doSleep(2);
		try {
			System.out.println(pool.get("test"));
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	private void doSleep(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
