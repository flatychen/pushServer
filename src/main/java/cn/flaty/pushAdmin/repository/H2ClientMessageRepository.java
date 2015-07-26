package cn.flaty.pushAdmin.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.flaty.NettyPush.entity.packet.ClientPacket;
import cn.flaty.NettyPush.repository.JdbcTemplateWrapper;
import cn.flaty.pushAdmin.entity.Message;
import cn.flaty.pushAdmin.entity.SendedMessage;

@Repository
public class H2ClientMessageRepository implements ClientMessageRepository {

	@Autowired
	private JdbcTemplateWrapper jdbc;

	@Override
	public boolean insertMessage(Message m) {
		String sql = " insert into tb_message( appkey,msgId, title ,content, flag , pushActionMixin ,createTime,expireTime) values(?,?,?,?,?,?,?,?)";
		return jdbc
				.saveORUpdate(
						sql,
						new Object[] { m.getAppKey(), new Date().getTime(),
								m.getTitle(), m.getContent(), m.getFlag(),
								m.getPushActionMixin(), new Date().getTime(),
								m.getExpireTime() }) == 1;
	}

	@Override
	public List<Message> queryClientMessage(ClientPacket clientInfo) {
		String sql = " select appkey,msgId, title ,content, flag , pushActionMixin ,createTime,expireTime  from  tb_message a where a.appkey = ? and a.expireTime >= ? and not exists ( select  *  from tb_sended_msg b where a.msgid = b.msgid and a.appkey=b.appkey)";
		return jdbc.queryForBeanList(sql, Message.class, new Object[] {
				clientInfo.getAppKey(), new Date().getTime() });
	}

	@Override
	public boolean insertSendedMessage(SendedMessage sm) {
		String sql = " insert into tb_sended_msg(appkey,did,msgid) values(?,?,?)";
		return jdbc.saveORUpdate(sql,
				new Object[] { sm.getAppKey(), sm.getDid(), sm.getMsgId() }) == 1;
	}

}
