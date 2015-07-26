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

import cn.flaty.NettyPush.entity.packet.ClientPacket;
import cn.flaty.NettyPush.entity.packet.GenericPacket;
import cn.flaty.NettyPush.entity.persitence.Client;
import cn.flaty.NettyPush.server.conn.NettyConnection;
import cn.flaty.NettyPush.utils.FastJsonUtils;
import cn.flaty.pushAdmin.entity.Message;
import cn.flaty.pushAdmin.entity.PushMessagePacket;
import cn.flaty.pushAdmin.entity.SendedMessage;
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
	 * @param client 
	 * 
	 * @param msg
	 */
	public void sendOnLineClient(Client client, PushMessagePacket pm) {

		Long msgId = this.saveMessage(client,pm);

		
		
		List<Client> clients = Collections.unmodifiableList(super
				.queryClients(client));
		NettyConnection conn = null;

		int size = clients.size();
		if (size > 0) {
			log.info("sending {} client message:{}", clients.size(),pm.toString());
			for (Client clientInfo : clients) {
				try {
					conn = pool.get(clientInfo.getDid());
					if (conn != null) {
						this.send(msgId, clientInfo, pm, conn);
					}
				} catch (Exception e) {
					log.error(e.getMessage());
					continue;
				}

			}
		} else {
			log.info("no client to send");
		}
		
	}

	private void send(long msgId, ClientPacket client, PushMessagePacket pm,
			NettyConnection conn) throws Exception {

		// 发送推送消息
		String _msg = (GenericPacket.server_push_text)
				+ FastJsonUtils.toJsonString(pm);
		conn.writeAndFlush(_msg);

		// 标记消息已发送
		SendedMessage cm = new SendedMessage();
		cm.setDid(client.getDid());
		cm.setAppKey(client.getAppKey());
		cm.setMsgId(msgId);
		clientMessageRepository.insertSendedMessage(cm);

	}

	/**
	 * 
	 * 保存消息于db中
	 * 
	 * @param pushMessage
	 * @return
	 * @author flatychen
	 * @param client 
	 */
	private long saveMessage(Client client, PushMessagePacket pushMessage) {
		Message message = new Message();
		try {
			PropertyUtils.copyProperties(message, pushMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 构造推送消息
		Date now = new Date();
		// 一小时过期
		message.setExpireTime(DateUtils.addHours(now, 1).getTime());
		message.setMsgId(now.getTime());
		message.setAppKey(client.getAppKey());
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
	public void sendForNewClient(final ClientPacket client,
			final NettyConnection conn) {

		final List<Message> messages = Collections
				.unmodifiableList(clientMessageRepository
						.queryClientMessage(client));

		if (messages.size() > 0) {
			task.submit(new Runnable() {
				@Override
				public void run() {
					for (Message message : messages) {
						PushMessagePacket pm = new PushMessagePacket();
						try {
							PropertyUtils.copyProperties(pm, message);
							send(message.getMsgId(), client, pm, conn);
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
