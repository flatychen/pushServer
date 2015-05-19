package cn.flaty.NettyPush.services;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Service;

import cn.flaty.NettyPush.entity.ClientInfo;
import cn.flaty.NettyPush.entity.GenericMessage;
import cn.flaty.NettyPush.entity.PushMessage;
import cn.flaty.NettyPush.entity.persitence.Client;
import cn.flaty.NettyPush.server.conn.NettyConnection;
import cn.flaty.NettyPush.utils.FastJsonUtils;
import cn.flaty.pushAdmin.entity.ClientMessage;
import cn.flaty.pushAdmin.entity.Message;
import cn.flaty.pushAdmin.repository.ClientMessageRepository;

@Service
public class PushService extends ConnPoolService {

	@Autowired
	private AsyncTaskExecutor task;

	@Autowired
	private ClientMessageRepository clientMessageRepository;

	private Logger log = LoggerFactory.getLogger(PushService.class);

	/**
	 * 推送 <br>
	 * FIXME 大用户？注意优化!! juc 包
	 * 
	 * @param msg
	 */
	public void sendOnLineClient(PushMessage pm) {

		Long msgId = this.saveMessage(pm);

		List<Client> clients = Collections.unmodifiableList(super
				.queryClients(new ClientInfo()));
		NettyConnection conn = null;
		for (Client clientInfo : clients) {
			try {
				conn = pool.get(clientInfo.getDid());
				if (conn != null) {
					this.send(conn, msgId, clientInfo.getDid(), pm);
				}
			} catch (Exception e) {
				log.error(e.getMessage());
				continue;
			}

		}
	}

	private void send(NettyConnection conn, Long msgId, String did,
			PushMessage pm) throws Exception {
		String _msg = (GenericMessage.server_push_text)
				+ FastJsonUtils.toJsonString(pm);
		conn.writeAndFlush(_msg);
		ClientMessage cm = new ClientMessage();
		cm.setDid(did);
		cm.setSendTime(new Date().getTime());
		cm.setMsgId(msgId);
		clientMessageRepository.insertMessageLog(cm);

	}

	/**
	 * 
	 * 保存消息于db和redis中
	 * 
	 * @param pushMessage
	 * @return
	 * @author flatychen
	 */
	private Long saveMessage(PushMessage pushMessage) {
		Message message = new Message();
		try {
			PropertyUtils.copyProperties(message, pushMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Date now = new Date();
		message.setAppkey("mypushTest");
		message.setExpireTime(DateUtils.addHours(now, 1).getTime());
		message.setMsgId(now.getTime());
		clientMessageRepository.insertMessage(message);
		return now.getTime();
	}

	/**
	 * 
	 * 为新连接的客户端推送
	 * 
	 * @param client
	 * @param conn
	 * @author flatychen
	 */
	public void sendNewClient(final ClientInfo client,
			final NettyConnection conn) {

		final List<Message> messages = Collections
				.unmodifiableList(clientMessageRepository
						.queryClientMessage(client));

		if (messages.size() > 0) {
			task.submit(new Runnable() {
				@Override
				public void run() {
					for (Message message : messages) {
						PushMessage pm = new PushMessage();
						try {
							PropertyUtils.copyProperties(pm, message);
							send(conn, message.getMsgId(), client.getDid(), pm);
						} catch (Exception e) {
							e.printStackTrace();
							continue;
						}

					}

				}
			});
		}

	}

}
