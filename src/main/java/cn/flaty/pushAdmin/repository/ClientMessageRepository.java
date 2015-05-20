package cn.flaty.pushAdmin.repository;

import java.util.List;

import cn.flaty.NettyPush.entity.packet.ClientPacket;
import cn.flaty.pushAdmin.entity.MessageLog;
import cn.flaty.pushAdmin.entity.SurviveMessage;

public interface ClientMessageRepository {

	public boolean insertMessageLog(SurviveMessage m);

	public boolean insertMessageLog(MessageLog messageLog);

	public List<SurviveMessage> queryClientMessage(ClientPacket clientInfo);

}
