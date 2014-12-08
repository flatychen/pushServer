package cn.flaty.NettyPush.utils;

import java.net.InetSocketAddress;

public class InetSocketUtils {

	public static String getHostName(InetSocketAddress socket) {
		return socket.getHostName();
	}

	public static String getPort(InetSocketAddress socket) {
		return socket.getPort()+"";
	}

	public static String getSocketAddress(InetSocketAddress socket) {
		return getHostName(socket) + ":" + getPort(socket);
	}
}
