package cn.flaty.NettyPush.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.flaty.NettyPush.entity.ClientInfo;
import cn.flaty.NettyPush.entity.persitence.Client;

@Repository
public class H2ClientRepository implements ClientRepository {

	@Autowired
	private JdbcTemplateWrapper jdbc;

	@Override
	public boolean insertClient(ClientInfo c) {
		String sql = " insert into tb_client(appkey,did,updateTime) values(?,?,?)";
		return jdbc.saveORUpdate(sql,
				new Object[] { c.getAppKey(),c.getDid(), new Date().getTime() }) == 1;
	}

	@Override
	public List<Client> queryClients(String appKey) {
		String sql = " select   appkey,did,updateTime from tb_client";
		return jdbc.queryForBeanList(sql, Client.class, null);
	}

	@Override
	public boolean delExpireClient() {
		Date now = new Date();
		Date d = new Date(now.getTime() - ClientRepository.second_db_live);
		String sql = " delete from tb_client where updateTime <= ? ";
		return jdbc.saveORUpdate(sql, new Object[] { d.getTime() }) >= 1;
	}

	@Override
	public boolean updateClient(ClientInfo c) {
		String sql = " update  tb_client set updateTime = ? where did = ?";
		return jdbc.saveORUpdate(sql,
				new Object[] { new Date().getTime(), c.getDid() }) == 1;
	}

	@Override
	public Client getClient(String did) {
		String sql = " select  appkey,did,updateTime from tb_client where did = ?";
		return jdbc.queryForBean(sql, Client.class, new Object[] { did });
	}

}
