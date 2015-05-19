package cn.flaty.NettyPush.repository;

import java.util.List;

import org.apache.commons.lang.time.DateUtils;

import cn.flaty.NettyPush.entity.ClientInfo;
import cn.flaty.NettyPush.entity.persitence.Client;

public interface ClientRepository {

	@SuppressWarnings("deprecation")
	public static long client_db_live_time = DateUtils.MILLIS_IN_SECOND * 30;

	public boolean insertClient(ClientInfo c);

	public Client getClient(String did);

	public List<Client> queryClients(String appKey);

	public boolean updateClient(ClientInfo c);

	public boolean delExpireClient();

}
