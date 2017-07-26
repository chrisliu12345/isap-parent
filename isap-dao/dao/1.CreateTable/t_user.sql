-- t_user
DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user
(
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(128) NOT NULL COMMENT '用户名称',
  login_name varchar(36) NOT NULL COMMENT '登录名',
  passwd varchar(64) NOT NULL COMMENT '用户密码',
  mobile char(11) default NULL COMMENT '移动电话',
  email varchar(36) default NULL COMMENT '邮箱',
  priority tinyint(4) unsigned NOT NULL COMMENT '用户级别',
  status tinyint(4) unsigned NOT NULL COMMENT '用户状态',
  lock_start_time datetime NOT NULL default CURRENT_TIMESTAMP COMMENT '锁定开始时间',
  lock_end_time datetime NOT NULL default CURRENT_TIMESTAMP COMMENT '锁定结束时间',
  login_ip varchar(20) DEFAULT NULL,
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_user PRIMARY KEY (id),
  CONSTRAINT uk_t_user UNIQUE (login_name)
)COMMENT= '用户信息表';