package cn.flaty.pushAdmin.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.flaty.NettyPush.services.PushService;
import cn.flaty.pushAdmin.entity.PushTextPacket;
import cn.flaty.pushAdmin.views.BaseDataWrapper;
import cn.flaty.pushAdmin.views.push.PushMessageForm;

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
	public BaseDataWrapper push(PushMessageForm pushMessageBean) {

		BaseDataWrapper json = new BaseDataWrapper();
		PushTextPacket pushMessage = null;

		// 消息序列化
		try {
			pushMessage = pushMessageBean.parsePushMessage();
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			return json;

		}

		// 推送消息
		try {
			pushService.sendOnLineClient(pushMessage);
		} catch (Exception e) {
			json.setSuccess(false);
			return json;
		}
		json.setSuccess(true);
		return json;
	}

}
