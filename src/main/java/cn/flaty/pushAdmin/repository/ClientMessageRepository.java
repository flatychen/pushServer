package cn.flaty.pushAdmin.repository;

import java.util.List;

import cn.flaty.NettyPush.entity.packet.ClientPacket;
import cn.flaty.pushAdmin.entity.Message;
import cn.flaty.pushAdmin.entity.SendedMessage;


public interface ClientMessageRepository {

	 public boolean insertMessage(Message msg);
	
	 public boolean insertSendedMessage(SendedMessage sm);
	
	 public List<Message> queryClientMessage(ClientPacket clientInfo);

}
