package cn.flaty.NettyPush.services;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.flaty.NettyPush.entity.packet.ClientPacket;
import cn.flaty.NettyPush.entity.persitence.Client;
import cn.flaty.NettyPush.repository.ClientRepository;
import cn.flaty.NettyPush.server.conn.NettyConnection;
import cn.flaty.NettyPush.server.conn.NettyConnectionPool;
import cn.flaty.NettyPush.utils.AssertUtils;

@Service
public abstract class ConnPoolService {

	private volatile boolean isRefleshing = false;

	@Autowired
	private ClientRepository clientInfoRepo;

	@Autowired
	protected NettyConnectionPool<String, NettyConnection> pool;

	protected List<Client> queryClients(ClientPacket client) {
		return clientInfoRepo.queryClients(client.getDid());
	}

	protected void saveClientInfo(ClientPacket client) {
		AssertUtils.notNull(client);
		AssertUtils.notNull(client.getDid());
		Client c = this.getClient(client.getDid());
		if (c == null) {
			clientInfoRepo.insertClient(client);
		} else {
			clientInfoRepo.updateClient(client);
		}

	}

	protected void resetClientExpire(ClientPacket client) {
		AssertUtils.notNull(client);
		AssertUtils.notNull(client.getDid());
		clientInfoRepo.updateClient(client);
	}

	protected void delExpireClientsOfDb() {
		this.isRefleshing = true;
		Timer refleshClientInfo = new Timer("refleshClientInfo");
		refleshClientInfo.schedule(new TimerTask() {
			@Override
			public void run() {
				clientInfoRepo.delExpireClient();
			}
		}, 1024, ClientRepository.client_db_live_time);
	}

	protected Client getClient(String did) {
		return clientInfoRepo.getClient(did);
	}

	protected boolean isRefleshClient() {
		return isRefleshing;
	}

}
