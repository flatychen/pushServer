drop table if exists tb_online_client;
drop table if exists tb_sended_msg;
drop table if exists tb_message;



-- 在线用户表
create table  tb_online_client(
appkey VARCHAR(100),
did VARCHAR(100),
appVer VARCHAR(20),
os VARCHAR(20),
expireTime  BIGINT
);

-- 已发送消息表
create table  tb_sended_msg(
appkey VARCHAR(100),
did VARCHAR(100),
msgId BIGINT
);

-- 消息日志表
create table  tb_message(
appkey VARCHAR(100),
msgId BIGINT,
title VARCHAR(60),
content VARCHAR(900),
flag int,
pushActionMixin VARCHAR(200),
createTime  BIGINT,
expireTime BIGINT
);






