package cn.flaty.NettyPush.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.flaty.NettyPush.nosql.RedisClientRepository;
import cn.flaty.NettyPush.server.conn.NettyConnection;
import cn.flaty.NettyPush.server.conn.NettyConnectionPool;

@Service
public abstract class ConnPoolService {

	@Autowired
	private RedisClientRepository clientRepo;

	@Autowired
	protected NettyConnectionPool<String, NettyConnection> pool;

}
