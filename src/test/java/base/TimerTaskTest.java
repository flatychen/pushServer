package base;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

import cn.flaty.repository.socketClient.ClientRepository;

public class TimerTaskTest {
	
	
	@Test
	public void test(){
		Timer refleshClientInfo = new Timer("refleshClientInfo");
		refleshClientInfo.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("baiwjgg");
			}
		}, 2000, 1000);
		
		
		
	}

}
