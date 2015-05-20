package cn.flaty.NettyPush.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.flaty.NettyPush.entity.packet.ClientPacket;
import cn.flaty.NettyPush.entity.packet.GenericPacket;
import cn.flaty.NettyPush.server.conn.NettyConnection;
import cn.flaty.NettyPush.utils.FastJsonUtils;

/**
 * @author Administrator
 * 
 */
@Service
public class ClientDispacherService extends ConnPoolService {

	@Autowired
	private PushService pushService;

	private static Logger log = LoggerFactory
			.getLogger(ClientDispacherService.class);

	/**
	 * 客户端报文分发
	 * 
	 * @param conn
	 * @param msg
	 */
	public void dispacher(NettyConnection conn, String data) {
		GenericPacket m = new GenericPacket(data);
		int commond = m.getCommond();

		// 新连接
		if (commond == GenericPacket.client_connected) {
			this.validateAndSave(conn, m.getMessage());
			// 心跳
		} else if (commond == GenericPacket.client_heart) {
			this.keepAliveOfDb(m.getMessage());
		} else {
			log.error(" invalid commond type .");
		}

	}

	/**
	 * 检测新连接合法性，并保存
	 * 
	 * @param conn
	 * @param message
	 */
	private void validateAndSave(NettyConnection conn, String message) {
		log.info("new client:{}", message);
		ClientPacket client = null;

		try {
			client = FastJsonUtils.praseToObject(message, ClientPacket.class);
		} catch (Exception e) {
			log.error("连接报文不合法: {}", e.getMessage());
			return;
		}

		// 检查是否有消息需要发送
		pushService.sendNewClient(client, conn);

		// 保存连接信息于本地连接池中和DB中
		super.saveClientInfo(client);
		pool.set(client.getDid(), conn);

		// 自动刷新DB中客户端数据
		// this.startRefleshClientOfDb();
	}

	private void startRefleshClientOfDb() {
		if (!super.isRefleshClient()) {
			super.delExpireClientsOfDb();
		}
	}

	/**
	 * 
	 * 维持数据库客户端更新
	 * 
	 * @param conn
	 * @param message
	 */
	private void keepAliveOfDb(String message) {

		log.info("heartBeat:{}", message);
		ClientPacket client = FastJsonUtils.praseToObject(message,
				ClientPacket.class);

		System.out.println(pool.get(client.getDid()));

		super.resetClientExpire(client);
		pool.touch(client.getDid());
	}

}
