create table  tb_client(appkey VARCHAR(100),did VARCHAR(100),updateTime  BIGINT);
create table  tb_message(appkey VARCHAR(100),msgId BIGINT,title VARCHAR(60),content VARCHAR(400),flag int,pushActionMixin VARCHAR(200),createTime  BIGINT,expireTime BIGINT);
create table  tb_client_message(did VARCHAR(100),msgid BIGINT,sendTime  BIGINT);





