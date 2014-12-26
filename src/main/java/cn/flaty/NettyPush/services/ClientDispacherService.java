package cn.flaty.NettyPush.services;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.flaty.NettyPush.model.ClientInfo;
import cn.flaty.NettyPush.server.conn.NettyConnection;

/**
 * @author Administrator
 *
 */
@Service
public class ClientDispacherService extends ConnPoolService {

	private static Logger log = LoggerFactory.getLogger(ClientDispacherService.class);
	

	/**
	 * 客户端报文分发
	 * @param conn
	 * @param msg
	 */
	public void dispacher(NettyConnection conn, String msg) {
		this.validateAndSave(conn,msg);
//	//	GenericMessage m = FastJsonUtils.praseToObject(msg,
//				GenericMessage.class);
//		int commond = m.getCommond();
//		switch (commond) {
//		// 新连接
//		case ConnPoolService.CONN_NEW:
//			this.validateAndSave(conn,m.getMessage());
//			break;
//		// 心跳	
//		case ConnPoolService.CONN_KEEP_HEART:
//			this.keepAlive(conn, m.getMessage());
//			break;
//		default:
//			log.warn("----> invalid commond type ");
//			break;
//		}
		
	}

	/**
	 * 检测新连接，并保存
	 * 
	 * @param conn
	 * @param message
	 */
	private void validateAndSave(NettyConnection conn, String message) {
		//ClientInfo client = FastJsonUtils.praseToObject(message, ClientInfo.class);
		// 保存客户端信息
		//super.saveClientInfo(client);
		
		// 开启客户端信息自动过期
//		if(!super.isRefleshClient()){
//			super.delexpireClients();
//		}
		ClientInfo client = new ClientInfo();
		client.setCid(new Date()+"");
		pool.set(client.getCid(), conn);
	}


	
	
	/**
	 * 
	 * 维持心跳连接
	 * @param conn
	 * @param message
	 */
	private void keepAlive(NettyConnection conn, String message) {
		//ClientInfo client = FastJsonUtils.praseToObject(message, ClientInfo.class);
//		super.resetClientExpire(client);
//		pool.touch(client.getCid());
	}
	
}
