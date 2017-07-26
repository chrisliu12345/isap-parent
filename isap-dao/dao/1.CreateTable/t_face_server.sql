-- t_face_server
DROP TABLE IF EXISTS t_face_server;
CREATE TABLE t_face_server
(
  id bigint NOT NULL AUTO_INCREMENT,
  ip varchar(36) NOT NULL COMMENT '服务器IP地址',
  port int NOT NULL COMMENT '服务器端口',
  Databaseip varchar(36) NOT NULL COMMENT '人脸数据库IP',
  DatabaseName varchar(36) NOT NULL COMMENT '人脸数据库数据名',
  username varchar(36) NOT NULL COMMENT '数据库用户',
  password varchar(36) NOT NULL COMMENT '数据库密码',
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_face_server PRIMARY KEY (id)
)COMMENT= '人脸服务器表';