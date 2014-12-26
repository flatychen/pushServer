package base;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

public class DateTimeUtilsTest {
	
	
	@Test
	public void test(){
		Date d = new Date();
		System.out.println(d);
		DateUtils.setSeconds(d, 20);
		System.out.println(d);
	}

	
	@Test
	public void test2(){
		Date d = new Date();
		System.out.println(d);
		long d2 = d.getTime() - DateUtils.MILLIS_PER_SECOND * 30;
		System.out.println(new Date(d2));
	}
}
