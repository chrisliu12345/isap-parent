-- t_dev_authdef
DROP TABLE IF EXISTS t_dev_authdef;
CREATE TABLE t_dev_authdef
(
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(128) NOT NULL COMMENT '设备权限名称',
  dev_type int NOT NULL COMMENT '设备类型',
  permission varchar(50) NOT NULL COMMENT '权限标识',
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_dev_authdef PRIMARY KEY (id)
)COMMENT= '设备权限信息表';