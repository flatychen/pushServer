package cn.flaty.pushAdmin.views.push;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.flaty.NettyPush.services.PushService;
import cn.flaty.NettyPush.utils.FastJsonUtils;
import cn.flaty.pushAdmin.services.PushServiceProxy;
import cn.flaty.pushAdmin.views.BaseDataWrapper;


@Controller
@RequestMapping("/pushMessage")
public class PushMessageController {
	
	private static Logger log = LoggerFactory.getLogger(PushMessageController.class);

	@Autowired
	private PushServiceProxy pushService;
	
	@RequestMapping("/new")
	public String news(){
		return "pushMessage/new";
	}
	
	
	
	@RequestMapping("/create")
	@ResponseBody
	public BaseDataWrapper create(@Valid PushMessageFormBean msg){
		log.info(msg.toString());
		return pushService.sendTest(msg);
	}
}
