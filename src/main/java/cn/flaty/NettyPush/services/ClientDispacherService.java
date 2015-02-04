package cn.flaty.NettyPush.services;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.flaty.NettyPush.entity.ClientInfo;
import cn.flaty.NettyPush.entity.GenericMessage;
import cn.flaty.NettyPush.server.conn.NettyConnection;
import cn.flaty.NettyPush.utils.FastJsonUtils;

/**
 * @author Administrator
 *
 */
@Service
public class ClientDispacherService extends ConnPoolService {

	private static Logger log = LoggerFactory
			.getLogger(ClientDispacherService.class);

	/**
	 * 客户端报文分发
	 *
	 * @param conn
	 * @param msg
	 */
	public void dispacher(NettyConnection conn, String msg) {
		this.validateAndSave(conn, msg);
		GenericMessage m = FastJsonUtils.praseToObject(msg,
				GenericMessage.class);
		int commond = m.getCommond();

		// 新连接
		if (commond == GenericMessage.client_new) {
			this.validateAndSave(conn, m.getMessage());

		// 心跳
		} else if (commond == GenericMessage.client_heart) {
			this.keepAlive(conn, m.getMessage());
		} else {
			log.warn("----> invalid commond type ");
		}

	}

	/**
	 * 检测新连接，并保存
	 *
	 * @param conn
	 * @param message
	 */
	private void validateAndSave(NettyConnection conn, String message) {
		ClientInfo client = FastJsonUtils.praseToObject(message,
				ClientInfo.class);
		super.saveClientInfo(client);
		if (!super.isRefleshClient()) {
			super.delexpireClients();
		}
		pool.set(client.getDid(), conn);
	}

	/**
	 *
	 * 维持心跳连接
	 *
	 * @param conn
	 * @param message
	 */
	private void keepAlive(NettyConnection conn, String message) {
		// ClientInfo client = FastJsonUtils.praseToObject(message,
		// ClientInfo.class);
		// super.resetClientExpire(client);
		// pool.touch(client.getCid());
	}

}
