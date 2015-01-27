package cn.flaty.NettyPush.services;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.flaty.NettyPush.entity.ClientInfo;
import cn.flaty.NettyPush.server.conn.NettyConnection;

@Service
public class PushService extends ConnPoolService {

	private Logger log = LoggerFactory.getLogger(PushService.class);

	public void send(String msg) {
		List<ClientInfo> clients = super.queryClientInfo(new ClientInfo());
		NettyConnection conn = null;
		for (ClientInfo clientInfo : clients) {
			try {
				conn = pool.get(clientInfo.getCid());
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}

			if (conn != null) {
				conn.writeAndFlush(msg);
			}
		}
	}

	public void sendTest(String msg) {
		if (StringUtils.isEmpty(msg)) {
			return;
		}
		Map<String, NettyConnection> pools = pool.asMap();
		Set<Map.Entry<String, NettyConnection>> sets = pools.entrySet();
		for (Entry<String, NettyConnection> entry : sets) {
			NettyConnection conn = entry.getValue();
			conn.writeAndFlush(msg);
		}

	}

}
