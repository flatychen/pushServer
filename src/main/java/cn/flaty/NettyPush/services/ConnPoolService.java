package cn.flaty.NettyPush.services;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.flaty.NettyPush.entity.ClientInfo;
import cn.flaty.NettyPush.server.conn.NettyConnection;
import cn.flaty.NettyPush.server.conn.NettyConnectionPool;
import cn.flaty.NettyPush.utils.AssertUtils;
import cn.flaty.pushAdmin.repository.socketClient.ClientRepository;



@Service
public abstract class ConnPoolService {
	

	private  volatile boolean isRefleshClient = false;
	
	@Autowired
	private ClientRepository clientInfoRepo;
	
	@Autowired
	protected NettyConnectionPool<String, NettyConnection> pool;
	
	protected static final int CONN_NEW = 100;

	protected static final int CONN_KEEP_HEART = 101;


	protected List<ClientInfo> queryClientInfo(ClientInfo client){
		return clientInfoRepo.queryClients(client);
	}
	
	
	protected void saveClientInfo(ClientInfo client){
		AssertUtils.notNull(client);
		AssertUtils.notNull(client.getCid());
		clientInfoRepo.insertClient(client);
	}
	
	protected void resetClientExpire(ClientInfo client){
		AssertUtils.notNull(client);
		AssertUtils.notNull(client.getCid());
		clientInfoRepo.updateClient(client);
	}
	
	
	protected void delexpireClients(){
		this.isRefleshClient = true;
		Timer refleshClientInfo = new Timer("refleshClientInfo");
		refleshClientInfo.schedule(new TimerTask() {
			@Override
			public void run() {
				clientInfoRepo.delExpireClient();
			}
		}, 1024, ClientRepository.second_30);
	}



	protected boolean isRefleshClient() {
		return isRefleshClient;
	}
	
	
	
}
