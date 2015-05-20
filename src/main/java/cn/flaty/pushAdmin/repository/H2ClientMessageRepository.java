package cn.flaty.pushAdmin.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.flaty.NettyPush.entity.packet.ClientPacket;
import cn.flaty.NettyPush.entity.persitence.Client;
import cn.flaty.NettyPush.repository.JdbcTemplateWrapper;
import cn.flaty.pushAdmin.entity.ClientMessage;
import cn.flaty.pushAdmin.entity.SurviveMessage;

@Repository
public class H2ClientMessageRepository implements ClientMessageRepository {

	@Autowired
	private JdbcTemplateWrapper jdbc;

	@Override
	public boolean insertMessage(SurviveMessage m) {
		String sql = " insert into tb_message(appkey,msgId, title ,content, flag , pushActionMixin ,createTime,expireTime) values(?,?,?,?,?,?,?,?)";
		return jdbc.saveORUpdate(
				sql,
				new Object[] { m.getAppkey(), new Date().getTime(),
						m.getTitle(), m.getContent(), m.getFlag(),
						m.getPushActionMixin(), m.getMsgId(),
						m.getExpireTime() }) == 1;
	}

	@Override
	public List<SurviveMessage> queryClientMessage(ClientPacket clientInfo) {
		String sql = " select appkey,msgId, title ,content, flag , pushActionMixin ,createTime,expireTime  from  tb_message a where a.appkey = ? and a.expireTime >= ? and not exists ( select  * from tb_client_message b where a.msgid = b.msgid )";
		return jdbc.queryForBeanList(sql, SurviveMessage.class, new Object[] {
				clientInfo.getAppKey(), new Date().getTime() });
	}

	@Override
	public boolean insertMessageLog(ClientMessage cm) {
		String sql = " insert into tb_client_message(did,msgid,sendtime) values(?,?,?)";
		return jdbc.saveORUpdate(
				sql,
				new Object[] { cm.getDid(),cm.getMsgId(),cm.getSendTime() }) == 1;
	}

}
