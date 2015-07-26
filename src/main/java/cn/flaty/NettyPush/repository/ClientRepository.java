package cn.flaty.NettyPush.repository;

import java.util.List;

import org.apache.commons.lang.time.DateUtils;

import cn.flaty.NettyPush.entity.packet.ClientPacket;
import cn.flaty.NettyPush.entity.persitence.Client;

public interface ClientRepository {

	@SuppressWarnings("deprecation")
	public static long client_db_live_time = DateUtils.MILLIS_IN_SECOND * 40;

	public boolean insertClient(ClientPacket c);

	public Client getClient(String did);

	public List<Client> queryClients(String appKey);

	public boolean touchClient(ClientPacket c);

	public boolean delExpireClient();

}
