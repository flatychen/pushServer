package cn.flaty.repository.socketClient;

import java.util.List;

import org.apache.commons.lang.time.DateUtils;

import cn.flaty.NettyPush.model.ClientInfo;

public interface ClientRepository {

	@SuppressWarnings("deprecation")
	public static long second_30 = DateUtils.MILLIS_IN_SECOND * 30;

	public boolean insertClient(ClientInfo c);

	public List<ClientInfo> queryClients(ClientInfo c);

	public boolean updateClient(ClientInfo c);

	public boolean delExpireClient();

}
