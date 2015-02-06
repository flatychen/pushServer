package cn.flaty.NettyPush.repository;

import java.util.List;

import org.apache.commons.lang.time.DateUtils;

import cn.flaty.NettyPush.entity.ClientInfo;

public interface ClientRepository {


    public static long second_db_live = DateUtils.MILLIS_IN_SECOND * 40;


    public boolean insertClient(ClientInfo c);

    public List<ClientInfo> queryClients(ClientInfo c);

    public boolean updateClient(ClientInfo c);

    public boolean delExpireClient();

}
