package cn.flaty.pushAdmin.services;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.flaty.NettyPush.model.GenericMessage;
import cn.flaty.NettyPush.services.PushService;
import cn.flaty.NettyPush.utils.FastJsonUtils;
import cn.flaty.pushAdmin.views.BaseDataWrapper;
import cn.flaty.pushAdmin.views.push.PushMessage;

@Service
public class PushServiceProxy  {

	@Autowired
	private PushService pushService;
	
	private Logger log = LoggerFactory.getLogger(PushServiceProxy.class);


	public BaseDataWrapper sendTest(PushMessage pushMessage) {
		BaseDataWrapper json = new BaseDataWrapper();
		String _msg = FastJsonUtils.toJsonString(pushMessage);
		if (StringUtils.isEmpty(_msg)) {
			json.setSuccess(false);
			return json;
		}
		try {
			pushService.sendTest((GenericMessage.server_push_text)+(_msg));
		} catch (Exception e) {
			json.setSuccess(false);
			return json;
		}
		json.setSuccess(true);
		return json;
	}

}
