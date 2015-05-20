package cn.flaty.NettyPush.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.flaty.NettyPush.entity.packet.ClientPacket;
import cn.flaty.NettyPush.entity.persitence.Client;

//@Repository
public class MysqlClientRepository implements ClientRepository {

	@Autowired
	private JdbcTemplateWrapper jdbc;

	@Override
	public boolean insertClient(ClientPacket c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Client getClient(String did) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> queryClients(String appKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateClient(ClientPacket c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delExpireClient() {
		// TODO Auto-generated method stub
		return false;
	}

}
