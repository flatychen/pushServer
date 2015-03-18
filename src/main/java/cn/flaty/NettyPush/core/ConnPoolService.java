package cn.flaty.NettyPush.core;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.flaty.NettyPush.clientRepo.ClientRepository;
import cn.flaty.NettyPush.model.ClientInfo;
import cn.flaty.NettyPush.server.conn.NettyConnection;
import cn.flaty.NettyPush.server.conn.NettyConnectionPool;
import cn.flaty.NettyPush.utils.AssertUtils;



/**
 * 
 * 保存客户端信息，使用DB
 * 
 * @author flatychen
 *
 */
//@Service

public abstract class ConnPoolService {

	private  volatile boolean isRefleshClient = false;
	
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
