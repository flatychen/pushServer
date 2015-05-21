package cn.flaty.nosql;

import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.time.StopWatch;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import redis.clients.jedis.JedisPool;
import cn.flaty.NettyPush.nosql.JedisTemplate;
import cn.flaty.NettyPush.nosql.JedisUtils;
import cn.flaty.spring.SpringContextTestCase;

@ContextConfiguration(locations = { "classpath:nosql/applicationRedis.xml" })
public class JedisTemplateTest extends SpringContextTestCase {

	private ExecutorService es;

	private static int threadSize = 10;
	private static int loopSize = 10000 * 100;

	@Autowired
	private JedisTemplate jedisTemplate;
	@Autowired
	private JedisPool jedisPool;

	@Before
	public void prepare() {
		JedisUtils.ping(jedisPool);
		es = Executors.newFixedThreadPool(threadSize);
	}

	@Test
	public void listTest() {
		jedisTemplate.lpush("users", "baiwjgg");
		jedisTemplate.lpush("users", "baiwjgg");
		jedisTemplate.lpush("users", "baiwjgg");
		assertEquals(3L, jedisTemplate.llen("users").longValue());
	}

	@Test
	public void mapPutTest() throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(1);
		jedisTemplate.flushDB();
		StopWatch watch = new StopWatch();
		watch.start();
		for (int i = 0; i < loopSize; i++) {
			final int j = i;
			es.submit(new Runnable() {
				@Override
				public void run() {
					jedisTemplate.hset("users", "name" + j + ":4.0.1", j
							+ "baiwjgg");
					if (j == (loopSize - 1)) {
						latch.countDown();
					}
				}

			});

		}
		watch.stop();
		latch.await();
		System.out.println("mapSet:" + watch.toString());
	}

	@Test
	public void mapGetTest() throws InterruptedException {
		StopWatch watch = new StopWatch();
		watch.start();
		Map<String, String> result = jedisTemplate.hgetAll("users");
		watch.stop();
		System.out.println("mapGet:" + watch.toString());
		System.out.println(result.size());
	}

	@Test
	public void setPutTest() throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(1);
		jedisTemplate.flushDB();
		StopWatch watch = new StopWatch();
		watch.start();
		for (int i = 0; i < loopSize; i++) {
			final int j = i;
			es.submit(new Runnable() {
				@Override
				public void run() {
					jedisTemplate.sadd("clients", j + "baiwjgg");
					if (j == (loopSize - 1)) {
						latch.countDown();
					}
				}

			});

		}
		latch.await();
		watch.stop();
		System.out.println("setSet:" + watch.toString());
	}

	@Test
	public void setGetTest() throws InterruptedException {
		StopWatch watch = new StopWatch();
		watch.start();
		Set<String> s = jedisTemplate.smembers("clients");
		watch.stop();
		System.out.println(s.size());
		System.out.println("getSet:" + watch.toString());
		Thread.sleep(600 * 1000);
	}

}
