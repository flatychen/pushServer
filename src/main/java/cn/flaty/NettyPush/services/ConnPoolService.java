package cn.flaty.NettyPush.services;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.flaty.NettyPush.entity.ClientInfo;
import cn.flaty.NettyPush.repository.ClientRepository;
import cn.flaty.NettyPush.server.conn.NettyConnection;
import cn.flaty.NettyPush.server.conn.NettyConnectionPool;
import cn.flaty.NettyPush.utils.AssertUtils;



@Service
public abstract class ConnPoolService {


	private  volatile boolean isRefleshing = false;

	@Autowired
	private ClientRepository clientInfoRepo;

	@Autowired
	protected NettyConnectionPool<String, NettyConnection> pool;


	protected List<ClientInfo> queryClientInfo(ClientInfo client){
		return clientInfoRepo.queryClients(client);
	}


	protected void saveClientInfo(ClientInfo client){
		AssertUtils.notNull(client);
		AssertUtils.notNull(client.getDid());
		clientInfoRepo.insertClient(client);
	}

	protected void resetClientExpire(ClientInfo client){
		AssertUtils.notNull(client);
		AssertUtils.notNull(client.getDid());
		clientInfoRepo.updateClient(client);
	}


	protected void delExpireClientsOfDb(){
		this.isRefleshing = true;
		Timer refleshClientInfo = new Timer("refleshClientInfo");
		refleshClientInfo.schedule(new TimerTask() {
			@Override
			public void run() {
				clientInfoRepo.delExpireClient();
			}
		}, 1024, ClientRepository.second_db_live);
	}



	protected boolean isRefleshClient() {
		return isRefleshing;
	}



}
