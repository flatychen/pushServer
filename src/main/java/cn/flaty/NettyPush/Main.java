package cn.flaty.NettyPush;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.flaty.NettyPush.server.Listener;
import cn.flaty.NettyPush.utils.beanFactoryUtils;
import cn.flaty.NettyPush.window.MainFrame;

public class Main {

	private final String LISTENER_NAME = "listener";

	private final String SPRING_CONFIG = "classpath:applicationContext.xml";

	private ApplicationContext context = null;

	public static void main(String args[]) {
		new Main().start();
	}

	private void start() {
		
		this.initSpringContext();
	
		MainFrame.initFrame();
		
		this.initListener();
		
		
	}

	private void initListener() {
		
		Listener listener = context.getBean(LISTENER_NAME, Listener.class);
		listener.start();
		
	}

	public void initSpringContext() {
		context = new ClassPathXmlApplicationContext(SPRING_CONFIG);
		beanFactoryUtils.setApplicationContext(context);
	}

}
