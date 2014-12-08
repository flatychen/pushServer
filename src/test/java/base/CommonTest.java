package base;

import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Test;

public class CommonTest {
	
	
	@Test
	public void CharsetTest(){
		Charset  c =  Charset.forName("gbk");
		System.out.println(c);
		char a[] = {'中','a','国'};
		System.out.println(Arrays.toString(a));
		
		
	}

}
