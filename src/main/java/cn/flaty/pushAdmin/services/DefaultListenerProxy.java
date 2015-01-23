package cn.flaty.pushAdmin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import cn.flaty.NettyPush.server.Listener;
import cn.flaty.NettyPush.utils.beanFactoryUtils;

@Component
public class DefaultListenerProxy  implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private TaskExecutor exe;
	
	@Autowired
	private Listener listener;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext context= event.getApplicationContext();
		// 父容器启动
		if(context.getParent() == null){
			beanFactoryUtils.setApplicationContext(context);
			exe.execute(new Runnable() {
				@Override
				public void run() {
					listener.start();
				}
			});;
		}
		
	}


	
	
	

}
