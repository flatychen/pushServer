package cn.flaty.NettyPush;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.flaty.NettyPush.server.Listener;
import cn.flaty.NettyPush.window.MainFrame;

public class Main  {

	private final String LISTENER_NAME = "listener";

	
	
	public static void main(String args[]) {
		new Main().initSpringContext();
	}

	public void initSpringContext() {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:applicationContext.xml");
		Listener listener = context.getBean(LISTENER_NAME, Listener.class);
		
		
	//	MainFrame.initFrame();
		
		
		listener.start();
		
		
	}
	
	
	

}
