package cn.flaty.NettyPush.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import cn.flaty.NettyPush.services.DeserializeService;


public  class beanFactoryUtils {
	
	
	private final static String DESERIALIZESERVICE_NAME = "deserializeService";
	
	private static ApplicationContext context;

	public static void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		beanFactoryUtils.context = applicationContext;
	}

	public static <T> T getBean(String name,Class<T> clazz){
		return context.getBean(name, clazz);
	}
	
	
	public static DeserializeService getDeserializeService(){
		return getBean(DESERIALIZESERVICE_NAME, DeserializeService.class);
	}


}
