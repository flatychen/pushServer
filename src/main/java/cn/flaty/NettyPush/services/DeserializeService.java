package cn.flaty.NettyPush.services;

import java.util.Date;

import org.springframework.stereotype.Service;

import cn.flaty.NettyPush.model.GenericMessage;
import cn.flaty.NettyPush.server.conn.NettyConnection;
import cn.flaty.NettyPush.utils.FastJsonUtils;

@Service
public class DeserializeService extends ConnPoolService {


	public void dispacher(NettyConnection conn, String msg) {
		GenericMessage m = FastJsonUtils.praseToObject(msg,
				GenericMessage.class);
		int commond = m.getCommond();
		switch (commond) {
		case ConnPoolService.CONN_NEW:
			this.validateAndSave(conn,m.getMessage());
			break;
		case ConnPoolService.CONN_KEEP_HEART:
			
			break;
		}
	}

	private void validateAndSave(NettyConnection conn, String message) {
		super.pool.set(new Date()+"", conn);
	}

	
	
	
}
