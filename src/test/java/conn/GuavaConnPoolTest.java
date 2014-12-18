package conn;

import org.junit.Test;

import cn.flaty.NettyPush.server.conn.GuavaConnPool;
import cn.flaty.NettyPush.server.conn.NettyConnectionPool;

public class GuavaConnPoolTest {

	@Test
	public void test(){
		
		NettyConnectionPool pool = new GuavaConnPool();
		
		pool.set("test", "55");
		
		
		
		this.doSleep(20);
		
		
		
		pool.get("test");
		
		this.doSleep(100);
		
		
		
	}
	
	
	private void doSleep(long time){
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
