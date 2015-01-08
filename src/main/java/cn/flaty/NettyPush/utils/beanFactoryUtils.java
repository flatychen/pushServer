package cn.flaty.NettyPush.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import cn.flaty.NettyPush.services.ClientDispacherService;
import cn.flaty.NettyPush.services.PushService;


public  class beanFactoryUtils {
	
	
	private final static String DESERIALIZESERVICE_NAME = "clientDispacherService";
	private final static String PUSHSERVICE_NAME = "pushService";
	
	private static ApplicationContext context;

	public static void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		beanFactoryUtils.context = applicationContext;
	}

	public static <T> T getBean(String name,Class<T> clazz){
		return context.getBean(name, clazz);
	}
	
	
	public static ClientDispacherService   getClientDispacherService(){
		return getBean(DESERIALIZESERVICE_NAME, ClientDispacherService.class);
	}

	
	public static PushService getPushService(){
		return getBean(PUSHSERVICE_NAME, PushService.class);
	}
	
}
