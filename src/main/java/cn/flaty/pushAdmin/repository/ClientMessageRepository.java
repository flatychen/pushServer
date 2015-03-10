package cn.flaty.pushAdmin.repository;

import java.util.List;

import cn.flaty.NettyPush.entity.ClientInfo;
import cn.flaty.NettyPush.entity.persitence.Client;
import cn.flaty.pushAdmin.entity.ClientMessage;
import cn.flaty.pushAdmin.entity.Message;

public interface ClientMessageRepository {


    public boolean insertMessage(Message m);

    public boolean insertMessageLog(ClientMessage cm);
    
    public List<Message> queryClientMessage(ClientInfo clientInfo);


}
