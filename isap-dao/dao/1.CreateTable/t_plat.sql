-- t_plat
DROP TABLE IF EXISTS t_plat;
CREATE TABLE t_plat
(
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(128) NOT NULL COMMENT '平台名称',
  vendor_type tinyint(4) unsigned NOT NULL COMMENT '厂家类型',
  access_type tinyint(4) unsigned NOT NULL COMMENT '接入类型',
  access_ip_address varchar(36) NOT NULL COMMENT '接入IP地址',
  access_port int unsigned NOT NULL COMMENT '接入端口',
  login_user varchar(128) NOT NULL COMMENT '用户名',
  login_passwd varchar(64) NOT NULL COMMENT '用户密码',
  status tinyint(4) unsigned NOT NULL COMMENT '接入状态',
  description varchar(255) NOT NULL default '' COMMENT '平台描述',
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_plat PRIMARY KEY (id),
  CONSTRAINT uk_t_plat UNIQUE (access_ip_address)
)COMMENT= '平台管理表';