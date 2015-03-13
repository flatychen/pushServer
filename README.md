nettyPusher
===========

#用netty写的TCP基础通信服务器 


##通信协议

	                简单的基础的自定义通信协议       
 	  	   +-------+----+-----+----------------+     
	bytes  |   4  |  1  | 3   |  ...           |
	 	   +------+-----+-----+----------------+     
	packet | 长度 | 编码 | 保留 | 包体           |
	       +------+-----+------+---------------+ 

##master分支
* 一个简单的netty tcp socket通信  
> 主要为netty的简单使用  
> java客户端详见 [基于nio的java客户端](https://github.com/flatychen/nettyPusherClient)

##web分支

*  实现简单的推送（android）  
*  使用h2+guava进行连接管理
*  心跳处理

为区分报文类型在包体加上**4字节**，**因业务不同**，故放包体中

  				包体       
 	  	   +--------+----------------+     
	bytes  |    4   |   ...          |
	 	   +--------+----------------+   
	packet | 报文类型|    报文内容     |
	       +--------+----------------+ 

> android客户端详见 [android客户端](https://github.com/flatychen/nettyPusherAndroid)
 

