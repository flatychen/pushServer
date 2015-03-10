package cn.flaty.pushAdmin.services;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.flaty.NettyPush.entity.PushMessage;
import cn.flaty.NettyPush.services.PushService;
import cn.flaty.NettyPush.utils.FastJsonUtils;
import cn.flaty.pushAdmin.entity.Message;
import cn.flaty.pushAdmin.repository.ClientMessageRepository;
import cn.flaty.pushAdmin.views.BaseDataWrapper;
import cn.flaty.pushAdmin.views.push.PushMessageFormBean;

@Service
public class PushServiceProxy {


	@Autowired
	private PushService pushService;

	private Logger log = LoggerFactory.getLogger(PushServiceProxy.class);


	/**
	 * 消息推送
	 *
	 * @param pushMessageBean
	 * @return
	 */
	public BaseDataWrapper push(PushMessageFormBean pushMessageBean) {

		BaseDataWrapper json = new BaseDataWrapper();
		PushMessage pushMessage = null;
		try {
			 pushMessage = pushMessageBean.parsePushMessage();
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			return json;

		}

		try {
			pushService.sendOnLineClient(pushMessage);;
		} catch (Exception e) {
			json.setSuccess(false);
			return json;
		}
		json.setSuccess(true);
		return json;
	}


	




}
