-- 在线用户表
create table  tb_online_client(
appkey VARCHAR(100),
did VARCHAR(100),
appVer VARCHAR(20),
os VARCHAR(20),
expireTime  BIGINT
);

-- 未过期消息表,幸存表
create table  tb_survive_msg(
appkey VARCHAR(100),
minAppVer VARCHAR(20),
minOs VARCHAR(20),
createTime  BIGINT,
expireTime BIGINT
);

-- 消息日志表
create table  tb_msg_log(
appkey VARCHAR(100),
msgId BIGINT,
title VARCHAR(60),
content VARCHAR(900),
flag int,
pushActionMixin VARCHAR(200),
createTime  BIGINT,
expireTime BIGINT
);






