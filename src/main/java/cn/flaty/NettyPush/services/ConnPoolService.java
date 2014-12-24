package cn.flaty.NettyPush.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.flaty.NettyPush.model.GenericMessage;
import cn.flaty.NettyPush.server.conn.NettyConnection;
import cn.flaty.NettyPush.server.conn.NettyConnectionPool;
import cn.flaty.NettyPush.utils.FastJsonUtils;

@Service
public class ConnPoolService {


	@Autowired
	protected NettyConnectionPool<String, NettyConnection> pool;
	
	protected static final int CONN_NEW = 100;

	protected static final int CONN_KEEP_HEART = 101;


	
	
	
}
